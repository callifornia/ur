package com.prokopiv.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

public class WebAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

    @Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		if(exception.getClass().isAssignableFrom(InternalAuthenticationServiceException.class)){
			logger.error("Нет подключения к Базе данных. Проверьте подключения к БД. (dbconfig.preperties)", exception);
			request.getSession(true).setAttribute("connect", false);
			setDefaultFailureUrl("/login");
//			response.sendRedirect("login");
		} 
		setDefaultFailureUrl("/loginError");
		super.onAuthenticationFailure(request, response, exception);
		
	}    
	
}
