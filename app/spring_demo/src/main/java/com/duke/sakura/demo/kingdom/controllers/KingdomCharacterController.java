package com.duke.sakura.demo.kingdom.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duke.sakura.demo.exception.SakuraException;
import com.duke.sakura.demo.kingdom.model.CharacterAttribute;
import com.duke.sakura.demo.kingdom.model.CharacterEntity;
import com.duke.sakura.demo.kingdom.service.CharacterService;

@RestController
@RequestMapping("/sakura/kingdom/character")
public class KingdomCharacterController {
	
	@Autowired
	private CharacterService characterService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> kingdomCharacterDetails(
		@RequestHeader String userId
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<CharacterEntity> characters = this.characterService.findActiveCharacters(userId);
			map.put("message", "Test Kingdom character details");
			map.put("body", characters);
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value = "/attributes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> kingdomCharacterAttributes(
		@RequestHeader String characterId
	) {
		Map<String, Object> map = new HashMap<>();
		try {
			CharacterAttribute attributes = this.characterService.findCharacterAttributes(characterId);
			map.put("message", "Test Kingdom character attributes");
			map.put("body", attributes);
			ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(map, HttpStatus.OK);
			return response;
		}catch (SakuraException se) {
			se.printStackTrace(System.out);
			return new ResponseEntity<Map<String, Object>>(se.buildExceptionResponse(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
