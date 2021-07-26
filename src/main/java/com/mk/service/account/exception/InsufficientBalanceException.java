package com.mk.service.account.exception;

public class InsufficientBalanceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String error) {
		super(error);
	}
}
