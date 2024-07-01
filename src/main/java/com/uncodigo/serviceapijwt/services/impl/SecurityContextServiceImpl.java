package com.uncodigo.serviceapijwt.services.impl;

import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.services.ISecurityContextService;
import com.uncodigo.serviceapijwt.services.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextServiceImpl implements ISecurityContextService {


    private final IUserService userService;

    public SecurityContextServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getUserAuthenticated() {
        Object principal = getAuthentication().getPrincipal();
        String username;
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userService.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
