package com.duke.sakura.kingdom.exception;

import com.duke.sakura.demo.exception.ResourceUnavailableException;

public class InvalidArgumentException extends ResourceUnavailableException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4538541947083767008L;

	public InvalidArgumentException(String resource, String argumentName) {
		super(resource, String.format("Invalid argument %s passed", argumentName));
	}

}
