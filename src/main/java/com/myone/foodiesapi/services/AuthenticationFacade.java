package com.myone.foodiesapi.services;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
}
