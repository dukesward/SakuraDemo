package com.duke.sakura.demo.data;

public class DataSourceTaskConfig {
	
	private String sessionId;
	
	private String serviceId;
	
	private String dataSourceId;
	
	public DataSourceTaskConfig(String sessionId, String serviceId, String dataSourceId) {
		this.sessionId = sessionId;
		this.serviceId = serviceId;
		this.dataSourceId = dataSourceId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
}
