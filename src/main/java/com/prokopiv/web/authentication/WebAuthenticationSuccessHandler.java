package com.prokopiv.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class WebAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static final Logger logger = LogManager.getLogger(WebAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		logger.info("User \"" + authentication.getName() + "\" successful log in.");
		setDefaultTargetUrl("/users");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
