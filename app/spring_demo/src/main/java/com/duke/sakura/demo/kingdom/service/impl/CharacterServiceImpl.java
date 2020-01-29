package com.duke.sakura.demo.kingdom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.model.CharacterAttribute;
import com.duke.sakura.demo.kingdom.model.CharacterEntity;
import com.duke.sakura.demo.kingdom.repository.CharacterAttributeRepository;
import com.duke.sakura.demo.kingdom.repository.CharacterEntityRepository;
import com.duke.sakura.demo.kingdom.service.CharacterService;
import com.duke.sakura.kingdom.exception.UserProfileException;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	@Autowired
	CharacterEntityRepository characterRepository;
	@Autowired
	CharacterAttributeRepository characterAttrRepository;

	@Override
	@SakuraDataSource(key="active_character")
	public List<CharacterEntity> findActiveCharacters(String userId) {
		return this.characterRepository.findActiveCharacterByUserId(userId);
	}

	@Override
	public String getDataSourceId() {
		return "kingdom.character";
	}

	@Override
	public CharacterEntity findMainCharacter(String userId) {
		List<CharacterEntity> characters = this.characterRepository.findByStatus("M");
		if(characters == null || characters.size() == 0) {
			throw new UserProfileException("No active main character exists for target user");
		}else if(characters.size() > 1) {
			throw new UserProfileException("User has more than one active main characters");
		}
		return characters.get(0);
	}

	@Override
	public CharacterAttribute findCharacterAttributes(String characterId) {
		return this.characterAttrRepository.findByCharacterId(characterId);
	}
}
