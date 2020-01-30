package com.duke.sakura.demo.exception;

public class ResourceUnavailableException extends SakuraException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3783542108802432342L;

	public ResourceUnavailableException(String resource, String errorMessage) {
		super("Resource " + resource + " unavailable due to -> " + errorMessage);
	}
}
