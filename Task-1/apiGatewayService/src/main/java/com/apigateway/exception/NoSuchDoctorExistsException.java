package com.apigateway.exception;

public class NoSuchDoctorExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;
	 
    public NoSuchDoctorExistsException() {}
 
    public NoSuchDoctorExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
