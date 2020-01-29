package com.duke.sakura.demo.kingdom.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EVENT_POOL")
public class EventPool implements SakuraEntityBasic {
	@Id
	@Column(name="event_id")
	private String eventId;

	@Column(name="title",unique=true)
	private String eventTitle;
	
	@Column(name="type")
	private String eventType;

	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="repetitive")
	private int repetitive;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publish_condition", nullable=true)
	private EventPublishCondition publishCondition;
	
	@Column(name="repeat_condition")
	private int repeatCondition;
	
	@Column(name="location_condition")
	private String locationCondition;
	
	@Column(name="definition_id")
	private String definitionId;
	
	@Column(name="quest_id")
	private String questId;
	
	@Column(name="offer_id")
	private String offerId;
	
	@Transient
	private List<QuestObjectiveRelation> questObjectiveRelations;

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public int getRepetitive() {
		return repetitive;
	}

	public void setRepetitive(int repetitive) {
		this.repetitive = repetitive;
	}

	public int getRepeatCondition() {
		return repeatCondition;
	}

	public void setRepeatCondition(int repeatCondition) {
		this.repeatCondition = repeatCondition;
	}

	public String getLocationCondition() {
		return locationCondition;
	}

	public void setLocationCondition(String locationCondition) {
		this.locationCondition = locationCondition;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public EventPublishCondition getPublishCondition() {
		return publishCondition;
	}

	public void setPublishCondition(EventPublishCondition publishCondition) {
		this.publishCondition = publishCondition;
	}
	
	public String getQuestId() {
		return questId;
	}

	public void setQuestId(String questId) {
		this.questId = questId;
	}

	public List<QuestObjectiveRelation> getQuest() {
		return questObjectiveRelations;
	}

	public void setQuest(List<QuestObjectiveRelation> questObjectiveRelations) {
		this.questObjectiveRelations = questObjectiveRelations;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
}
