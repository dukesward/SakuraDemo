package com.duke.sakura.demo.kingdom.service;

import com.duke.sakura.demo.kingdom.model.OfferPool;
import com.duke.sakura.demo.service.SakuraDataService;

public interface UserOfferService extends SakuraDataService {
	
	public OfferPool getOfferReward(String offerId);
	
	public void processOfferConfirm(String userProfileId, String offerId);
}
