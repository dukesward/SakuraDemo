package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="QUEST_OBJECTIVE")
public class QuestObjectiveRelation {
	@Id
	@Column(name="quest_id")
	private String questId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reward_id", nullable=false)
	private RewardPool reward;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="objective_id", nullable=false)
	private ObjectivePool objective;

	public String getQuestId() {
		return questId;
	}

	public void setQuestId(String questId) {
		this.questId = questId;
	}

	public ObjectivePool getObjective() {
		return objective;
	}

	public void setObjective(ObjectivePool objective) {
		this.objective = objective;
	}
	
	public RewardPool getReward() {
		return reward;
	}

	public void setReward(RewardPool reward) {
		this.reward = reward;
	}

	public String toString() {
		return "objectiveId = " + this.getObjective().getObjectiveId() + ", "
				+ "objective_type = " + this.objective.getObjectiveType();
	}
}
