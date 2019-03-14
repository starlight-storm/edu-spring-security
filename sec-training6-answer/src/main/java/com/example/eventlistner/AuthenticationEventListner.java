package com.example.eventlistner;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListner {
	@EventListener
	public void authenticationSuccess(AuthenticationSuccessEvent e) {
		System.out.println("*** 認証成功: " + e.getAuthentication().getName());
	}
	
	@EventListener
	public void authenticationBadCredential(AuthenticationFailureBadCredentialsEvent e) {
		System.out.println("*** 認証失敗: " + e.getAuthentication().getName());
	}
}
