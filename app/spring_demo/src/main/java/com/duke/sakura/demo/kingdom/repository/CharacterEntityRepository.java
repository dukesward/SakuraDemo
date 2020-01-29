package com.duke.sakura.demo.kingdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.duke.sakura.demo.kingdom.model.CharacterEntity;

public interface CharacterEntityRepository extends JpaRepository<CharacterEntity, String> {
	@Query(
		value="SELECT * FROM character_pool c "
			+ "WHERE "
			+ "c.user_id = ?1 AND "
			+ "(c.status = 'A' OR c.status = 'M')",
		nativeQuery=true
	)
	List<CharacterEntity> findActiveCharacterByUserId(String status);
	
	List<CharacterEntity> findByStatus(String status);
}
