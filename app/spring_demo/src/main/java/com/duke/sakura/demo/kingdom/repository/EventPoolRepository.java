package com.duke.sakura.demo.kingdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.duke.sakura.demo.kingdom.model.EventPool;

@Repository
public interface EventPoolRepository extends JpaRepository<EventPool, String> {
	@Query(
		value="SELECT * FROM event_pool e "
			+ "WHERE NOT EXISTS "
			+ "(SELECT * FROM user_event_record u WHERE u.user_id = ?1 AND u.event_id = e.event_id) AND "
			+ "e.repetitive = 1 AND "
			+ "(e.location_condition = ?2 OR e.location_condition = '0')",
		nativeQuery=true
	)
	public List<EventPool> findNonRepetitiveEvents(String userProfileId, String locationId);
	
	public EventPool findByEventId(String eventId);
}
