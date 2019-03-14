package com.example.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/top.html").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .defaultSuccessUrl("/top.html")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/top.html")
                .and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("{noop}userpassword").authorities("ROLE_USER")
                    .and()
                .withUser("admin").password("{noop}adminuser").authorities("ROLE_ADMIN");
    }
}
