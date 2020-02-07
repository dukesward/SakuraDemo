package com.duke.sakura.demo.kingdom.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duke.sakura.demo.auth.model.SakuraUserSession;
import com.duke.sakura.demo.exception.SakuraInvalidRequestException;
import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.annotation.SakuraEventSource;
import com.duke.sakura.demo.kingdom.model.EventPool;
import com.duke.sakura.demo.kingdom.model.SakuraEntityBasic;
import com.duke.sakura.demo.kingdom.model.UserEventRecord;
import com.duke.sakura.demo.kingdom.model.UserSession;
import com.duke.sakura.demo.kingdom.repository.EventPoolRepository;
import com.duke.sakura.demo.kingdom.repository.UserEventRecordRepository;
import com.duke.sakura.demo.kingdom.service.EventService;
import com.duke.sakura.demo.kingdom.service.UserOfferService;
import com.duke.sakura.demo.kingdom.service.UserProfileConditionService;
import com.duke.sakura.demo.service.SakuraDataService;
import com.duke.sakura.demo.service.SakuraSessionManager;
import com.duke.sakura.demo.service.SakuraTaskManager;

@Service
public class EventServiceImpl implements EventService {
	
	private static String[] offerTypes = {"pub-offer"};
	static Map<String, List<EventPool>> eventCacheStack = new HashMap<>();
	
	static {
		eventCacheStack.put("global", new ArrayList<>());
	}

	@Autowired
	EventPoolRepository eventRepository;
	@Autowired
	UserEventRecordRepository userEventRecordRepository;
	@Autowired
	UserProfileConditionService userProfileConditionService;
	@Autowired
	UserOfferService userOfferService;
	@Autowired
	SakuraTaskManager taskManager;
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
	SakuraSessionManager sessionManager;
	
	@Override
	public String getDataSourceId() {
		return "kingdom.event";
	}

	@Override
	public List<EventPool> getGlobalEvents(String userProfileId, String locationId) {
		List<EventPool> events = new ArrayList<>();
		if(eventCacheStack.size() > 0) {
			// local stack only stores events with defined expire time
			// if stack has already been injected with events, add them to list
			events.addAll(this.fetchLocalEventCache("global"));
			events.addAll(this.flushLocalEventCache(userProfileId));
		}
		this.doGetGlobalEvents();
		return events
				.stream()
				.filter(event -> this.userProfileConditionService.testCondition(userProfileId, event.getPublishCondition().getScriptId()))
				.collect(Collectors.toList());
	}
	
	public void doGetGlobalEvents() {
		SakuraUserSession session = this.sessionManager.getUserSession();
		this.taskManager.produceTask(session.getSessionId(), this.getDataSourceId(), "event_global_once");
	};

	@Override
	@SakuraDataSource(key="event_global_once")
	public void getNonRepetitiveGlobalEvent(String userProfileId, String locationId) {
		List<EventPool> events = this.eventRepository.findNonRepetitiveEvents(userProfileId, locationId);
		String dataSourceId = this.getDataSourceId();
		events.stream().forEach(event -> SakuraDataService.putServiceEntityCache(dataSourceId + event.getEventId(), event));
		this.injectLocalEventCache(userProfileId, events);
	};
	
	@Override
	public EventPool getEvent(String eventId) {
		String eventUniqueId = this.getDataSourceId() + eventId;
		EventPool event = (EventPool) SakuraDataService.getServiceEntityCache(eventUniqueId);
		if(event == null) {
			// event is not fetched before, has to fetch from data source
			event = this.eventRepository.findByEventId(eventId);
			SakuraDataService.putServiceEntityCache(eventUniqueId, event);
		}
		return event;
	}

	@SakuraEventSource(key="events")
	public boolean processEvents() {
		return true;
	}
	
	@Transactional
	@Override
	public void confirmEvent(String userProfileId, String eventId) {
		UserEventRecord eventRecord = this.userEventRecordRepository.getByEventIdAndUserId(eventId, userProfileId);
		// if user event not recorded before, create new record, otherwise update
		if(this.isEventConditionSatisfied(userProfileId, eventId)) {
			if(eventRecord == null) {
				System.out.println("target event has not been confirmed by user yet");
				eventRecord = new UserEventRecord(userProfileId, eventId);
			}
			int pushCount = eventRecord.getPublishCount() + 1;
			eventRecord.setPublishCount(pushCount);
			this.userEventRecordRepository.save(eventRecord);
		}else {
			throw new SakuraInvalidRequestException("User not authorized to confirm target event");
		}
		
		try {
			if(this.isOffer(eventId)) {
				EventPool event = this.getEvent(eventId);
				this.userOfferService.processOfferConfirm(userProfileId, event.getOfferId());
			}
		}catch (Exception e) {
			throw new SakuraInvalidRequestException("Not able to confirm event due to -> " + e.getMessage());
		}
	}
	
	public boolean isEventConditionSatisfied(String userProfileId, String eventId) {
		return true;
	}
	
	public boolean isOffer(String eventId) {
		return Arrays.asList(offerTypes).contains(this.getEvent(eventId).getEventType());
	}
	
	private List<EventPool> fetchLocalEventCache(String cacheId) {
		List<EventPool> temp = new ArrayList<>();
		if(eventCacheStack.get(cacheId) != null) {
			eventCacheStack.get(cacheId).forEach(cache -> temp.add(cache));
		}
		return temp;
	}
	
	private List<EventPool> flushLocalEventCache(String cacheId) {
		List<EventPool> temp = this.fetchLocalEventCache(cacheId);
		eventCacheStack.put(cacheId, null);
		return temp;
	}
	
	private void injectLocalEventCache(String cacheId, List<EventPool> events) {
		if(eventCacheStack.get(cacheId) == null) {
			eventCacheStack.put(cacheId, events);
		}else {
			eventCacheStack.get(cacheId).addAll(events);
		}
	}
	
}
