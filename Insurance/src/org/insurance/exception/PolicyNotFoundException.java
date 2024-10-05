package org.insurance.exception;

public class PolicyNotFoundException extends Exception {
	
	//unique identifier of this class
	private static final long serialVersionUID = 1L;

	// pass message to the Exception class constructor
	public PolicyNotFoundException(String message) {
		super(message);
	}
}
