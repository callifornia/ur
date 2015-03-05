package com.prokopiv.web.exception;



public class DataBaseException extends ApplicationException {	
	
	private static final long serialVersionUID = 5878696000000832683L;
//	private static final Logger logger = LogManager.getLogger(ApplicationException.class);

	
	public DataBaseException() {
		super();
	}
	
	public DataBaseException(String message){
		super(message, new Exception());
	}
	public DataBaseException(String message, Exception exception) {
		super(message, exception);
//		logger.warn(message, exception);
	}

}
