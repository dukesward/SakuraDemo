package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "EVENT_PUBLISH_CONDITION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EventPublishCondition implements SakuraEntityBasic {
	@Id
	@Column(name="code")
	private int code;
	
	@Column(name="topic")
	private String topic;
	
	@Column(name="script_id")
	private int scriptId;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}
}
