package com.duke.sakura.demo.kingdom.service;

import java.util.List;

import com.duke.sakura.demo.kingdom.model.EventPool;
import com.duke.sakura.demo.kingdom.model.QuestPool;
import com.duke.sakura.demo.service.SakuraDataService;

public interface EventService extends SakuraDataService {
	
	public EventPool getEvent(String eventId);
	
	public List<EventPool> getGlobalEvents(String userProfileId, String locationId);
	
	public void getNonRepetitiveGlobalEvent(String userProfileId, String locationId);
	
	public void confirmEvent(String userProfileId, String eventId);
}
