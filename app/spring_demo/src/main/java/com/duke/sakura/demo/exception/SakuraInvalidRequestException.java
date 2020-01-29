package com.duke.sakura.demo.exception;

public class SakuraInvalidRequestException extends SakuraException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 617483502613246354L;

	public SakuraInvalidRequestException(String errorMessage) {
		super("Invalid request caused by -> " + errorMessage);
	}
}
