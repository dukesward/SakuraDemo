import { KingdomEventController } from './../../events/event-controller.component';
import { KingdomEventBasic } from 'src/app/sakura/agartha/entities/kingdom/event.global.entity';
import { Component, Input } from "@angular/core";
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { utils } from '../../../../../../common-utils';

@Component({
	selector: 'kingdom-quest-reward',
	templateUrl: './quest-reward.component.html',
  styleUrls: [
    '../../../../../sakura.theme.scss',
    '../../../kingdom.theme.scss',
    '../../event-wrapper/event-wrapper.component.scss',
    './quest-reward.component.scss'
  ]
})
export class KingdomQuestReward extends KingdomEventController {

	@Input() entity: KingdomEventBasic;
	
	constructor(protected schemaManager: SakuraSchemaManager) {
		super(schemaManager);
		this.schemaCache.put("kingdom-event-exception", this.schemaManager.loadSchema("kingdom-event", "exception"));
	}

	getQuests(): any[] {
    return utils.objectToArray(this.buildQuests());
	}
	
	getObjectiveContent(questItem: any): string {
		var objective = questItem.objective;
		if(objective) {
			var seriesId = objective.seriesId.toLowerCase();
			return this.getContentSchema("objective_" + seriesId);
		}
	}

	getObjectiveMessage(questItem: any): string {
		var objective = questItem.objective;
		if(objective) {
			var seriesId = objective.seriesId.toLowerCase();
			return this.getContentSchema("message_" + seriesId);
		}
	}

	buildQuests(): any {
    var quests = {};
    var items = this.entity.quests;
    for(var i=0; i<items.length; i++) {
      var objective = items[i].objective;
      if(objective && !quests[objective.objectiveId]) {
        quests[objective.objectiveId] = {
          questId: items[i].questId,
          title: this.entity.title,
          objective: objective,
          rewards: []
        };
      }
      if(items[i].reward) {
        quests[objective.objectiveId].rewards.push(this.processRewardItem(items[i].reward));
      }
    }
    return quests;
	}
	
	processRewardItem(reward: any): any {
		reward.iconSrc = this.processIconSource(reward);
		reward.itemInfo = this.processItemInfo(reward);
		return reward;
	}

	processIconSource(reward: any): string {
		switch(reward.rewardType) {
			case 'experience':
				return 'misc/experience';
			break;
		}
	}

	processItemInfo(reward: any): string {
		var output = "";
		switch(reward.rewardType) {
			case 'experience':
				var amount = "";
				if(reward.amountType === 'F') {
					amount = reward.factor || 0;
				}
				output = this.getEventSchema('quest-wrapper:quest-reward-experience', { amount: amount });
		}
		return output;
	}

	ngOnInit(): void {}
}