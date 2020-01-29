package com.duke.sakura.demo.kingdom.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.duke.sakura.demo.kingdom.entity.UserEventRecordId;

@Entity
@Table(name = "USER_EVENT_RECORD")
@IdClass(UserEventRecordId.class)
public class UserEventRecord implements SakuraEntityBasic {
	@Id
	@Column(name="event_id")
	private String eventId;
	
	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="publish_count")
	private int publishCount;
	
	@Column(name="update_time")
	private Timestamp updateTime;
	
	public UserEventRecord() {
		this.publishCount = 0;
	}
	
	public UserEventRecord(String userId, String eventId) {
		this.userId = userId;
		this.eventId = eventId;
		this.publishCount = 0;
	}

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

	public int getPublishCount() {
		return publishCount;
	}

	public void setPublishCount(int publishCount) {
		this.publishCount = publishCount;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
