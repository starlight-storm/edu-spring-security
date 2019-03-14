package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class Training7AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Training7AuthorizationServerApplication.class, args);
	}
}
