package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
	
	UserProfile findByUuid(String uuid);
	
	UserProfile findByName(String name);
}
