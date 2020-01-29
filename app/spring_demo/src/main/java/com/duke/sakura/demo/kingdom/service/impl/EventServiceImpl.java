package com.duke.sakura.demo.kingdom.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duke.sakura.demo.exception.SakuraInvalidRequestException;
import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.annotation.SakuraEventSource;
import com.duke.sakura.demo.kingdom.model.EventPool;
import com.duke.sakura.demo.kingdom.model.UserEventRecord;
import com.duke.sakura.demo.kingdom.repository.EventPoolRepository;
import com.duke.sakura.demo.kingdom.repository.UserEventRecordRepository;
import com.duke.sakura.demo.kingdom.service.EventService;
import com.duke.sakura.demo.kingdom.service.UserOfferService;
import com.duke.sakura.demo.kingdom.service.UserProfileConditionService;
import com.duke.sakura.demo.service.SakuraDataService;

@Service
public class EventServiceImpl implements EventService {
	
	private static String[] offerTypes = {"pub-offer"};

	@Autowired
	EventPoolRepository eventRepository;
	@Autowired
	UserEventRecordRepository userEventRecordRepository;
	@Autowired
	UserProfileConditionService userProfileConditionService;
	@Autowired
	UserOfferService userOfferService;
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public String getDataSourceId() {
		return "kingdom.event";
	}
	
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

	@Override
	@SakuraDataSource(key="event_global_once")
	public List<EventPool> getNonRepetitiveGlobalEvent(String userProfileId, String locationId) {
		List<EventPool> events = this.eventRepository.findNonRepetitiveEvents(userProfileId, locationId);
		String dataSourceId = this.getDataSourceId();
		events.stream().forEach(event -> SakuraDataService.putServiceEntityCache(dataSourceId + event.getEventId(), event));
		return events;
	};
	
	@Override
	public List<EventPool> getGlobalEvents(String userProfileId, String locationId) {
		List<EventPool> events = new ArrayList<>();
		events.addAll(this.getNonRepetitiveGlobalEvent(userProfileId, locationId));
		return events
				.stream()
				.filter(event -> this.userProfileConditionService.testCondition(userProfileId, event.getPublishCondition().getScriptId()))
				.collect(Collectors.toList());
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
	
}
