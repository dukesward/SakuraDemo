package com.duke.sakura.demo.exception;

public class SakuraRestServiceException extends SakuraException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1111506914186038757L;

	public SakuraRestServiceException(String errorMessage) {
		super("Rest service call failed due to -> " + errorMessage);
	}
}
