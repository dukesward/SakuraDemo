package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duke.sakura.demo.kingdom.model.UserSession;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, String> {
	
	UserSession findBySessionId(String sessionId);
}
