package com.example.demo.spring;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	private static ServletContext servletContext;

	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		
		HttpSession session = event.getSession();
		System.out.println("New session:" + session.getId());

		final ServletContext context = session.getServletContext();
		context.setAttribute(session.getId(), session);
		
		if(servletContext == null)
		{
			servletContext = context;
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub

	}
	
	public static HttpSession getSession(final String sessionId) {
		HttpSession session = (HttpSession)servletContext.getAttribute(sessionId);
		
		return session;
	}

}
