package com.duke.sakura.demo.kingdom.entity;

import java.io.Serializable;

public class QuestObjectiveId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1696803084095462250L;

	private String questId;
	
	private String rewardId;

	public String getQuestId() {
		return questId;
	}

	public void setQuestId(String questId) {
		this.questId = questId;
	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}
}
