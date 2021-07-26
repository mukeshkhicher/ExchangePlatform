package com.mk.service.exchange.domain.exception;

public class BorrowFailedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BorrowFailedException(String error) {
		super(error);
	}
}
