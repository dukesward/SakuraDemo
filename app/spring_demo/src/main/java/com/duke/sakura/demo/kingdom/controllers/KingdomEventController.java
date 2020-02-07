package com.duke.sakura.demo.kingdom.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duke.sakura.demo.exception.SakuraException;
import com.duke.sakura.demo.exception.SakuraInvalidRequestException;
import com.duke.sakura.demo.kingdom.model.EventPool;
import com.duke.sakura.demo.kingdom.model.QuestObjectiveRelation;
import com.duke.sakura.demo.kingdom.service.EventService;
import com.duke.sakura.demo.kingdom.service.QuestService;

@RestController
@RequestMapping("/sakura/kingdom/event")
public class KingdomEventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private QuestService questService;
	
	@RequestMapping(value="/global", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Map<String, Object>> kingdomEventGlobal(
		@RequestHeader String userProfileId,
		@RequestHeader String locationId,
		@RequestHeader String sessionId
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			// get all global events
			List<EventPool> events = this.eventService.getGlobalEvents(userProfileId, locationId);
			// link quest entity from quest id (if not null) of target event
			events.stream().forEach(event -> {
				// we are manually fetching linked quests here to avoid confusion
				List<QuestObjectiveRelation> questObjectiveRelations = this.questService.findQuestObjectiveByQuestId(event.getQuestId());
				// System.out.println(questObjectiveRelations);
				event.setQuest(questObjectiveRelations);
			});
			map.put("message", "Test Kingdom event global");
			map.put("body", events);
			map.put("reqStatus", "pending");
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value="/schema", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Map<String, Object>> kingdomEventSchema() {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("message", "Test Kingdom event schema");
			map.put("body", EventPool.getSchema());
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value="/confirm", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> kingdomConfirmEvent(
		@RequestHeader String userProfileId,
		@RequestBody Map<String, String> form
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			String eventId = form.get("eventId");
			if(eventId != null) {
				this.eventService.confirmEvent(userProfileId, eventId);
				map.put("message", "success");
				ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
				return response;
			}else {
				throw new SakuraInvalidRequestException("Event id is not provided");
			}
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
