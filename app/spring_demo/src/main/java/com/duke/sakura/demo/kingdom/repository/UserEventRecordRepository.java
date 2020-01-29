package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.UserEventRecord;

public interface UserEventRecordRepository extends JpaRepository<UserEventRecord, String> {
	
	public UserEventRecord getByEventIdAndUserId(String eventId, String userId);
}
