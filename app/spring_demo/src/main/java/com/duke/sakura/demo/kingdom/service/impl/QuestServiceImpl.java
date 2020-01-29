package com.duke.sakura.demo.kingdom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.kingdom.model.QuestObjectiveRelation;
import com.duke.sakura.demo.kingdom.model.QuestPool;
import com.duke.sakura.demo.kingdom.repository.QuestObjectiveRelationRepository;
import com.duke.sakura.demo.kingdom.repository.QuestPoolRepository;
import com.duke.sakura.demo.kingdom.service.QuestService;

@Service
public class QuestServiceImpl implements QuestService {
	@Autowired
	QuestPoolRepository questRepository;
	@Autowired
	QuestObjectiveRelationRepository questObjectiveRepository;

	@Override
	public String getDataSourceId() {
		return "kingdom.quest";
	}
	
	@Override
	public QuestPool getLinkedQuest(String questId) {
		if(questId != null) {
			return this.questRepository.findByQuestId(questId);
		}
		return null;
	}

	@Override
	public List<QuestObjectiveRelation> findQuestObjectiveByQuestId(String questId) {
		return this.questObjectiveRepository.findByQuestId(questId);
	}

}
