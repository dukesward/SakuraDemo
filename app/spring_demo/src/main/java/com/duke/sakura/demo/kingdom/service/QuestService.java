package com.duke.sakura.demo.kingdom.service;

import java.util.List;

import com.duke.sakura.demo.kingdom.model.QuestObjectiveRelation;
import com.duke.sakura.demo.kingdom.model.QuestPool;
import com.duke.sakura.demo.service.SakuraDataService;

public interface QuestService extends SakuraDataService {
	
	public QuestPool getLinkedQuest(String questId);
	
	public List<QuestObjectiveRelation> findQuestObjectiveByQuestId(String questId);
}
