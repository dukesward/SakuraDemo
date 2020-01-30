package com.duke.sakura.demo.exception;

public class SakuraDataSourceException extends SakuraException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -717656334687152419L;

	public SakuraDataSourceException(String errorMessage) {
		super("Data source error caused by -> " + errorMessage);
	}
}
