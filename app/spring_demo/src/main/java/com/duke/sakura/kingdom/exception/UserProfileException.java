package com.duke.sakura.kingdom.exception;

import com.duke.sakura.demo.exception.SakuraDataSourceException;

public class UserProfileException extends SakuraDataSourceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6622182258484060266L;

	public UserProfileException(String errorMessage) {
		super(errorMessage);
	}

}
