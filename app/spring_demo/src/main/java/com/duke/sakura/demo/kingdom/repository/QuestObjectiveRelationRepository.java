package com.duke.sakura.demo.kingdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duke.sakura.demo.kingdom.model.QuestObjectiveRelation;

public interface QuestObjectiveRelationRepository extends JpaRepository<QuestObjectiveRelation, String> {

	List<QuestObjectiveRelation> findByQuestId(String questId);
}
