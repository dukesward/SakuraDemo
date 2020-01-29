package com.duke.sakura.demo.kingdom.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.exception.SakuraDataSourceException;
import com.duke.sakura.demo.exception.SakuraRestServiceException;
import com.duke.sakura.demo.kingdom.model.EquipPool;
import com.duke.sakura.demo.kingdom.model.ItemPool;
import com.duke.sakura.demo.kingdom.model.OfferPool;
import com.duke.sakura.demo.kingdom.model.RewardPool;
import com.duke.sakura.demo.kingdom.repository.RewardPoolRepository;
import com.duke.sakura.demo.kingdom.runnable.DataSourceTask;
import com.duke.sakura.demo.kingdom.service.DataSourceManager;
import com.duke.sakura.demo.kingdom.service.UserOfferService;
import com.duke.sakura.demo.kingdom.utils.KingdomRestServicePostResponse;
import com.duke.sakura.demo.kingdom.utils.KingdomRestServiceProvider;
import com.duke.sakura.demo.service.SakuraDataService;

@Service
public class UserOfferServiceImpl implements UserOfferService {
	
	private static final String OFFER_TYPE_EVENT_GIFT = "event_gift";
	
	@Autowired
	KingdomRestServiceProvider kingdomRestServiceProvider;
	@Autowired
	DataSourceManager dataSourceManager;
	@Autowired
	RewardPoolRepository rewardPoolRepository;

	@Override
	public String getDataSourceId() {
		return "kingdom.user.offer";
	}
	
	@Override
	public OfferPool getOfferReward(String offerId) {
		OfferPool offer = (OfferPool) SakuraDataService.getServiceEntityCache(this.getDataSourceId() + offerId);
		if(offer == null) {
			offer = this.rewardPoolRepository.findByOfferId(offerId);
			SakuraDataService.putServiceEntityCache(this.getDataSourceId() + offerId, offer);
		}
		RewardPool reward = offer.getReward();
		if(reward != null) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				ItemPool item = reward.getItem();
				DataSourceTask task = this.dataSourceManager.getDataSourceExecutor(reward.getRewardType());
				EquipPool equipItem = (EquipPool) task.invokeNow(item.getInfoId());
				map.put("type", equipItem.getType());
				map.put("subtype", equipItem.getSubtype());
				map.put("quality", equipItem.getQuality());
				map.put("qualityLevel", equipItem.getQualityLevel());
				map.put("price", equipItem.getPrice());
				map.put("attrs", equipItem.getAttributes());
				item.setInfo(map);
			}catch (Exception e) {
				throw new SakuraDataSourceException(
					String.format("Data source executor %s can not be invoked: " + e.toString(), reward.getRewardType()));
			}
		}
		return offer;
	}

	@Override
	public void processOfferConfirm(String userProfileId, String offerId) {
		OfferPool offer = this.getOfferReward(offerId);
		String offerType = offer.getOfferType();
		if(OFFER_TYPE_EVENT_GIFT.equals(offerType)) {
			RewardPool reward = offer.getReward();
			int amount = reward.getFactor();
			this.processEventGiftConfirm(userProfileId, reward.getItem(), amount);
		}
	}
	
	protected void processEventGiftConfirm(String userProfileId, ItemPool item, int amount) {
		Map<String, Integer> items = new HashMap<>();
		items.put(item.getItemId(), amount);
		KingdomRestServicePostResponse response = this.kingdomRestServiceProvider.KingdomItemInventoryAdd(userProfileId, items);
		String message = response.getMessage();
		System.out.println(message);
		if(!"success".equals(message)) {
			throw new SakuraRestServiceException(message);
		}
	}

}
