package com.duke.sakura.kingdom.exception;

import com.duke.sakura.demo.exception.SakuraException;

public class ConditionScriptException extends SakuraException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8372534316392889207L;

	public ConditionScriptException(String errorMessage) {
		super("Invoking condition script failed due to -> " + errorMessage);
	}

}
