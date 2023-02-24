package com.lti.demo.exception;

public class DepartmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7182905945314218841L;

	public DepartmentNotFoundException() {
		super();
	}

	public DepartmentNotFoundException(String message) {
		super(message);
	}

}
