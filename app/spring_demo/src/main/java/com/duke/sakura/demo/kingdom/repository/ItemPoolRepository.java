package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.ItemPool;

public interface ItemPoolRepository extends JpaRepository<ItemPool, String> {
	
	ItemPool findByItemId(String itemId);
}
