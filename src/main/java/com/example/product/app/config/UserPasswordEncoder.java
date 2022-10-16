package com.example.product.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordEncoder implements PasswordEncoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPasswordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString().toUpperCase();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        LOGGER.warn("User trying to access secured routes in application");
        return rawPassword.toString().toUpperCase().equals(encodedPassword);
    }
}
