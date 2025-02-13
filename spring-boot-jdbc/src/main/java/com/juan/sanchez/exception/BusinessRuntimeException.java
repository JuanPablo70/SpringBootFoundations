package com.juan.sanchez.exception;

public class BusinessRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 804026094096945067L;

	public BusinessRuntimeException(String message) {
		super(message);
	}

}