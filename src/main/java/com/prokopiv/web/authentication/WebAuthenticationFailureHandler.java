package com.prokopiv.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class WebAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

    @Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		if(exception.getClass().isAssignableFrom(InternalAuthenticationServiceException.class)){
			logger.error("Нет подключения к Базе данных. Проверьте подключения к БД. (dbconfig.preperties)", exception);
			request.getSession(true).setAttribute("connect", false);
			setDefaultFailureUrl("/login");
		} 
		setDefaultFailureUrl("/loginError");
		super.onAuthenticationFailure(request, response, exception);
	}    	
}