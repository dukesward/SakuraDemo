package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.UserInventory;

public interface UserInventoryRepository extends JpaRepository<UserInventory, String> {
	
	UserInventory findByUserId(String userId);
}
