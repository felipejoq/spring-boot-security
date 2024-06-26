package com.uncodigo.serviceapijwt.configs.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenJwtConfig {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 3600000L);
    public static final Date ISSUED_AT = new Date();
    public static final String CONTENT_TYPE = "application/json";
    public static final String CHAR_SET_UTF_8 = "UTF-8";
}
