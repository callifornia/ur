package com.prokopiv.web.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ServiceException extends ApplicationException {

	private static final long serialVersionUID = 1224608816354861720L;
	private static final Logger logger = LogManager.getLogger(ApplicationException.class);

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message, Exception exception){
		super(message, exception);
		logger.warn(message, exception);
	}
}

