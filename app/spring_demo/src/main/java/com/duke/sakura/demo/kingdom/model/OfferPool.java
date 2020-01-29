package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OFFER_POOL")
public class OfferPool implements SakuraEntityBasic {
	@Id
	@Column(name="offer_id")
	private String offerId;
	
	@Column(name="offer_type")
	private String offerType;
	
	@OneToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="reward_id", nullable=false)
	private RewardPool reward;

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public RewardPool getReward() {
		return reward;
	}

	public void setReward(RewardPool reward) {
		this.reward = reward;
	}
}
