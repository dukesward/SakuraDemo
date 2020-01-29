package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "OBJECTIVE_POOL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ObjectivePool implements SakuraEntityBasic {
	@Id
	@Column(name="objective_id")
	private String objectiveId;
	
	@Column(name="difficulty_level")
	private int difficultyLevel;
	
	@Column(name="objectiveType")
	private String objectiveType;
	
	@Column(name="series_id")
	private String seriesId;

	public String getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(String objectiveId) {
		this.objectiveId = objectiveId;
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getObjectiveType() {
		return objectiveType;
	}

	public void setObjectiveType(String objectiveType) {
		this.objectiveType = objectiveType;
	}

	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

}
