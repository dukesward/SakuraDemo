package com.duke.sakura.demo.kingdom.entity;

import java.io.Serializable;

public class UserEventRecordId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2695642168674745698L;

	private String eventId;
	
	private String userId;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
