package com.prokopiv.web.exception;


public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = -6859564543419857060L;

	public ApplicationException() {
		super();
	}	
	
	public ApplicationException(String message, Exception ex) {
		super(message, ex);
	}

}
