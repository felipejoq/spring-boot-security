package com.uncodigo.serviceapijwt.services;

import com.uncodigo.serviceapijwt.models.User;
import org.springframework.security.core.Authentication;

public interface ISecurityContextService {
    Authentication getAuthentication();
    User getUserAuthenticated();
}
