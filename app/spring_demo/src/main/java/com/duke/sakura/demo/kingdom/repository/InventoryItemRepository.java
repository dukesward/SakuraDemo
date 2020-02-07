package com.duke.sakura.demo.kingdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duke.sakura.demo.kingdom.model.InventoryItem;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {

	List<InventoryItem> findByInventoryId(String inventoryId);
	
	InventoryItem findByInventoryIdAndItemId(String userId, String itemId);
}
