package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "QUEST_POOL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuestPool implements SakuraEntityBasic {
	@Id
	@Column(name="quest_id")
	private String questId;
	
	@Column(name="require_level")
	private int requireLevel;
	
	@Column(name="objective_number")
	private int objectiveNumber;

	public String getQuestId() {
		return questId;
	}

	public void setQuestId(String questId) {
		this.questId = questId;
	}

	public int getRequireLevel() {
		return requireLevel;
	}

	public void setRequireLevel(int requireLevel) {
		this.requireLevel = requireLevel;
	}

	public int getObjectiveNumber() {
		return objectiveNumber;
	}

	public void setObjectiveNumber(int objectiveNumber) {
		this.objectiveNumber = objectiveNumber;
	}
}
