package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.OfferPool;

public interface RewardPoolRepository extends JpaRepository<OfferPool, String> {
	
	OfferPool findByOfferId(String offerId);
}
