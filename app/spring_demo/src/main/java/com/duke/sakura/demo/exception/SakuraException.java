package com.duke.sakura.demo.exception;

import java.util.HashMap;
import java.util.Map;

public class SakuraException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9182441827854387697L;

	public SakuraException(String errorMessage) {
		super("Service currently not available due to -> " + errorMessage);
	}
	
	public Map<String, Object> buildExceptionResponse() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", this.getMessage());
		return response;
	}
}
