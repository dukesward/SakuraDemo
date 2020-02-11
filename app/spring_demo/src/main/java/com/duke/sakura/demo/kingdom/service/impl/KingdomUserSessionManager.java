package com.duke.sakura.demo.kingdom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.auth.model.SakuraUserSession;
import com.duke.sakura.demo.kingdom.repository.UserSessionRepository;
import com.duke.sakura.demo.service.SakuraSessionManager;

@Service
public class KingdomUserSessionManager implements SakuraSessionManager {
	
	private String sessionId;
	
	@Autowired
	UserSessionRepository userSessionRepository;

	@Override
	public SakuraUserSession getUserSession() {
		return this.userSessionRepository.findBySessionId(sessionId);
	}
}
