package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
public class Training7MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Training7MessageServiceApplication.class, args);
	}
	
	@Configuration
	@EnableResourceServer
	static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/**")
					.access("#oauth2.hasScope('hello')");
		}
	}
}
