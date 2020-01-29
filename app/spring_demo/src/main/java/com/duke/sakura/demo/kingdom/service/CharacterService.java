package com.duke.sakura.demo.kingdom.service;

import java.util.List;

import com.duke.sakura.demo.kingdom.model.CharacterAttribute;
import com.duke.sakura.demo.kingdom.model.CharacterEntity;
import com.duke.sakura.demo.service.SakuraDataService;

public interface CharacterService extends SakuraDataService {
	
	public List<CharacterEntity> findActiveCharacters(String userId);
	
	public CharacterEntity findMainCharacter(String userId);
	
	public CharacterAttribute findCharacterAttributes(String characterId);
}
