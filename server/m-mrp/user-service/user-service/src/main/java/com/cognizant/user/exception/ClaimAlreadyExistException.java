package com.cognizant.user.exception;

public class ClaimAlreadyExistException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClaimAlreadyExistException(String message) {
		super(message);
	}

}
