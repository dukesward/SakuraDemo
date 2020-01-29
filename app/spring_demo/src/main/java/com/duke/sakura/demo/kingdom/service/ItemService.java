package com.duke.sakura.demo.kingdom.service;

import java.util.List;

import com.duke.sakura.demo.kingdom.model.ItemPool;
import com.duke.sakura.demo.kingdom.model.OfferPool;
import com.duke.sakura.demo.service.SakuraDataService;

public interface ItemService extends SakuraDataService {
	
	public List<ItemPool> getInventoryItems(String userProfileId);
	
	public void putInventoryItem(String userProfileId, String itemId, int amount);
}
