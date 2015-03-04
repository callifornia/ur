package com.prokopiv.web.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DataBaseException extends Exception {	
	
	private static final long serialVersionUID = 5878696000000832683L;
	private static final Logger logger = LogManager.getLogger(DataBaseException.class);
	
	public DataBaseException(Exception exception, String message) {
		logger.warn(message, exception);
	}
	

}
