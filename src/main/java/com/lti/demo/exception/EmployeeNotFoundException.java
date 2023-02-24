package com.lti.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7182905945314218841L;

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
