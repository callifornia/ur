package com.prokopiv.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class WebLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	private final Logger logger = LogManager.getLogger(getClass());
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if(authentication != null){
			logger.info("User \"" + authentication.getName() + "\" sucessful log out.");
		}	
		setDefaultTargetUrl("/login");
		super.onLogoutSuccess(request, response, authentication);
	}
}
