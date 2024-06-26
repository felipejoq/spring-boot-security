package com.uncodigo.serviceapijwt.configs.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uncodigo.serviceapijwt.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.uncodigo.serviceapijwt.configs.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;
        String email = null;
        String password = null;

        try {
            if (request.getInputStream() != null && request.getInputStream().available() > 0) {
                user = new ObjectMapper().readValue(request.getInputStream(), User.class);
                if (user != null) {
                    email = user.getEmail();
                    password = user.getPassword();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = springUser.getUsername();
        Collection<? extends GrantedAuthority> authorities = springUser.getAuthorities();

        String token = Jwts.builder()
                .subject(username)
                .claim("authorities", authorities)
                .signWith(SECRET_KEY)
                .expiration(EXPIRATION_TIME)
                .issuedAt(ISSUED_AT)
                .compact();

        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("email", username);
        body.put("message", "Login successful!");

        response.setContentType(CONTENT_TYPE);
        response.addHeader(HEADER_AUTHORIZATION, TOKEN_PREFIX + token);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        Map<String, String> body = new HashMap<>();
        body.put("message", "Authentication failed!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(CONTENT_TYPE);
    }
}
