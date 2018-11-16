package com.learnandresearch.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSucessHandler{// implements AuthenticationSuccessHandler {
	
//		private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//		@Override
//		public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
//			redirectStrategy.sendRedirect(request, response, "/home");
//		}
}
