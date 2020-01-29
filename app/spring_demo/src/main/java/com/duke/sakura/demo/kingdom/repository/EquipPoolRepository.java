package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.EquipPool;

public interface EquipPoolRepository extends JpaRepository<EquipPool, String> {
	
	public EquipPool findByEquipId(String equipId);
}
