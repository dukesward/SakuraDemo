package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "REWARD_POOL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RewardPool {
	@Id
	@Column(name="reward_id")
	private String rewardId;
	
	@Column(name="reward_type")
	private String rewardType;
	
	@Column(name="amount_type")
	private String amountType;
	
	@Column(name="factor")
	private int factor;
	
	@OneToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="item_id", nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private ItemPool item;

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}

	public ItemPool getItem() {
		return item;
	}

	public void setItem(ItemPool item) {
		this.item = item;
	}
}
