package com.duke.sakura.demo.kingdom.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.exception.SakuraDataSourceException;
import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.model.EquipPool;
import com.duke.sakura.demo.kingdom.model.EventPool;
import com.duke.sakura.demo.kingdom.model.InventoryItem;
import com.duke.sakura.demo.kingdom.model.ItemPool;
import com.duke.sakura.demo.kingdom.model.OfferPool;
import com.duke.sakura.demo.kingdom.model.RewardPool;
import com.duke.sakura.demo.kingdom.model.UserInventory;
import com.duke.sakura.demo.kingdom.repository.EquipPoolRepository;
import com.duke.sakura.demo.kingdom.repository.InventoryItemRepository;
import com.duke.sakura.demo.kingdom.repository.ItemPoolRepository;
import com.duke.sakura.demo.kingdom.repository.RewardPoolRepository;
import com.duke.sakura.demo.kingdom.repository.UserInventoryRepository;
import com.duke.sakura.demo.kingdom.runnable.DataSourceTask;
import com.duke.sakura.demo.kingdom.service.DataSourceManager;
import com.duke.sakura.demo.kingdom.service.ItemService;
import com.duke.sakura.demo.service.SakuraDataService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemPoolRepository itemPoolRepository;
	@Autowired
	EquipPoolRepository equipPoolRepository;
	@Autowired
	UserInventoryRepository userInventoryRepository;
	@Autowired
	InventoryItemRepository iventoryItemRepository;

	@Override
	public String getDataSourceId() {
		return "kingdom.item";
	}

	@Override
	public List<ItemPool> getInventoryItems(String userProfileId) {
		UserInventory userInventory = this.userInventoryRepository.findByUserId(userProfileId);
		List<InventoryItem> inventory = this.iventoryItemRepository.findByInventoryId(userInventory.getInventoryId());
		return inventory
				.stream()
				.map(i -> this.itemPoolRepository.findByItemId(i.getItemId()))
				.collect(Collectors.toList());
	}
	
	@Override
	public void putInventoryItem(String userProfileId, String itemId, int amount) {
		UserInventory userInventory = this.userInventoryRepository.findByUserId(userProfileId);
		String inventoryId = userInventory.getInventoryId();
		InventoryItem item = this.iventoryItemRepository.findByInventoryIdAndItemId(inventoryId, itemId);
		if(item != null) {
			item.setStackAmount(item.getStackAmount() + amount);
		}else {
			item = new InventoryItem();
			item.setInventoryId(inventoryId);
			item.setItemId(itemId);
			item.setCreateDate(new Date());
			item.setStackAmount(amount);
		}
		this.iventoryItemRepository.save(item);
	}

	@SakuraDataSource(key="equipment")
	public EquipPool getEquipment(String equipId) {
		String itemUniqueId = this.getDataSourceId() + equipId;
		EquipPool equipment = (EquipPool) SakuraDataService.getServiceEntityCache(itemUniqueId);
		if(equipment == null) {
			equipment = this.equipPoolRepository.findByEquipId(equipId);
			SakuraDataService.putServiceEntityCache(itemUniqueId, equipment);
		}
		return equipment;
	}

}
