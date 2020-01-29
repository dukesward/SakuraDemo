package com.duke.sakura.demo.kingdom.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duke.sakura.demo.exception.SakuraException;
import com.duke.sakura.demo.kingdom.model.ItemPool;
import com.duke.sakura.demo.kingdom.model.OfferPool;
import com.duke.sakura.demo.kingdom.service.ItemService;
import com.duke.sakura.demo.kingdom.service.UserOfferService;

@RestController
@RequestMapping("/sakura/kingdom/item")
public class KingdomItemController {
	
	@Autowired
	ItemService itemService;
	@Autowired
	UserOfferService offerService;
	
	@RequestMapping(value="/reward", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Map<String, Object>> kingdomGetReward(
		@RequestHeader String offerId
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			OfferPool reward = this.offerService.getOfferReward(offerId);
			map.put("message", "Test Kingdom get reward");
			map.put("body", reward);
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value="/inventory/all", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Map<String, Object>> kingdomGetInventory(
		@RequestHeader String userProfileId
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<ItemPool> items = this.itemService.getInventoryItems(userProfileId);
			map.put("message", "Test Kingdom get inventory");
			map.put("body", items);
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value="/inventory/equipments", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Map<String, Object>> kingdomGetEquipments(
		@RequestHeader String userProfileId
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<ItemPool> items = this.itemService
					.getInventoryItems(userProfileId)
					.stream().filter(item -> "equipment".equals(item.getType()))
					.collect(Collectors.toList());
			map.put("message", "Test Kingdom get user equipments");
			map.put("body", items);
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value="/inventory/add", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> kingdomAddItemToInventory(
		@RequestHeader String userProfileId,
		@RequestBody Map<String, Integer> form
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			form.entrySet()
			.stream()
			.forEach(entry -> this.itemService.putInventoryItem(userProfileId, entry.getKey(), entry.getValue()));
			map.put("message", "success");
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
