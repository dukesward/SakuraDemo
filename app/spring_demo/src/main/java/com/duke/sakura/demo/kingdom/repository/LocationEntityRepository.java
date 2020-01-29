package com.duke.sakura.demo.kingdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.LocationEntity;

public interface LocationEntityRepository extends JpaRepository<LocationEntity, String> {
	
	LocationEntity findByUuid(String uuid);
	
	LocationEntity findByName(String name);
	
	List<LocationEntity> findByParent(String parent);
}
