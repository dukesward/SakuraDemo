package com.duke.sakura.demo.kingdom.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duke.sakura.demo.exception.SakuraException;
import com.duke.sakura.demo.kingdom.model.LocationEntity;
import com.duke.sakura.demo.kingdom.model.UserProfile;
import com.duke.sakura.demo.kingdom.service.LocationService;
import com.duke.sakura.demo.kingdom.service.UserProfileService;

@RestController
@RequestMapping("/sakura/kingdom/user")
public class KingdomUserProfileController {
	
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	LocationService locationService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> kingdomUserProfile(@RequestHeader String userProfileId) {
		Map<String, Object> map = new HashMap<>();
		try {
			UserProfile userProfile = this.userProfileService.findUserProfileById(userProfileId);
			Map<String, Object> userData = new HashMap<>();
			userData.put("name", userProfile.getName());
			userData.put("userId", userProfile.getUuid());
			userData.put("locationId", userProfile.getLocationId());
			userData.put("message", "Test Kingdom user profile");
			map.put("body", userData);
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value = "/location", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> kingdomLocationProfile(@RequestHeader String locationId) {
		Map<String, Object> map = new HashMap<>();
		try {
			LocationEntity locationProfile = this.locationService.findLocationById(locationId);
			Map<String, Object> userData = new HashMap<>();
			userData.put("name", locationProfile.getName());
			userData.put("userId", locationProfile.getUuid());
			userData.put("parent", locationProfile.getParent());
			userData.put("enName", locationProfile.getLocationId());
			userData.put("message", "Test Kingdom location profile");
			map.put("body", userData);
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
