package com.duke.sakura.demo.kingdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {

	List<InventoryItem> findByInventoryId(String inventoryId);
	
	InventoryItem findByInventoryIdAndItemId(String userId, String itemId);
}
