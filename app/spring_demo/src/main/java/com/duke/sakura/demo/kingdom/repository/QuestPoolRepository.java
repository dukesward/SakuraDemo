package com.duke.sakura.demo.kingdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.QuestPool;

public interface QuestPoolRepository extends JpaRepository<QuestPool, String> {
	
	QuestPool findByQuestId(String questId);
}
