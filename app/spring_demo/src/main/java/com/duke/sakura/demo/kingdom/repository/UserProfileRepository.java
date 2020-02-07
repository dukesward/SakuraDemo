package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duke.sakura.demo.kingdom.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
	
	UserProfile findByUuid(String uuid);
	
	UserProfile findByName(String name);
}
