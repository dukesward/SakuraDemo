package com.duke.sakura.demo.kingdom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.duke.sakura.demo.auth.model.SakuraUserSession;

@Entity
@Table(name = "USER_SESSION")
public class UserSession implements SakuraUserSession {
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="session_id")
	private String sessionId;
	
	@Column(name="validate_time")
	private int validateTime;
	
	@Column(name="create_time")
	private Date createTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getValidateTime() {
		return validateTime;
	}

	public void setValidateTime(int validateTime) {
		this.validateTime = validateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
