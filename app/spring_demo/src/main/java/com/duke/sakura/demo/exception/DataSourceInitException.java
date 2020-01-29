package com.duke.sakura.demo.exception;

public class DataSourceInitException extends SakuraDataSourceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1073232765072994686L;

	public DataSourceInitException(String dataSourceKey, String errorMessage) {
		super(String.format("Error initiating data source key [%s] due to: " + errorMessage, dataSourceKey));
	}

}
