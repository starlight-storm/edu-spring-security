package com.example.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.example.config.SecurityConfig;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SpringSecurityInitializer() {
        super(SecurityConfig.class);
    }

    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}