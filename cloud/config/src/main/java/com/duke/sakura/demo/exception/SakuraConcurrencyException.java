package com.duke.sakura.demo.exception;

public class SakuraConcurrencyException extends SakuraException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7082548915267465262L;

	public SakuraConcurrencyException(String errorMessage) {
		super("Thread pool failed to work caused by -> " + errorMessage);
	}
}
