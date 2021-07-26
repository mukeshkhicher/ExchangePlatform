package com.mk.service.user.exception;

public class MemberAlreadyExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberAlreadyExistsException(String error) {
		super(error);
	}
}
