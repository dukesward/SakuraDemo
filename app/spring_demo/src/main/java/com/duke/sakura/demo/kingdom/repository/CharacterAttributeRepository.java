package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.CharacterAttribute;

public interface CharacterAttributeRepository extends JpaRepository<CharacterAttribute, String> {
	
	CharacterAttribute findByCharacterId(String characterId);
}
