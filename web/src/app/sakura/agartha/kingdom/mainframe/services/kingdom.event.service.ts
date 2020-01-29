import { AgarthaEventBasic } from './../../../entities/utility/agartha.event';
import { KingdomActionHandler } from './kingdom.action.handler';
import { ApiProxyComponent } from 'src/app/modules/common/api-proxy/api-proxy.component';
import { KingdomEventBasic } from './../../../entities/kingdom/event.global.entity';
import { KingdomQuestEntity } from './../../../entities/kingdom/quest.entity';
import { Injectable } from "@angular/core";
import { KingdomContentService } from '../../services/kingdom.content.service';
import { EntityBasic } from 'src/app/models/entity.basic';
import { KingdomUserService } from './kingdom.user.service';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { KingdomCharacterService } from './kingdom.character.service';
import { KingdomItemService } from './kingdom.item.service';
import { SakuraValidatorBasic } from 'src/app/models/utility/validator.basic';
import { SakuraEntityValidationProxy } from 'src/app/sakura.entity.validator';

@Injectable({
    providedIn: "root"
})
export class KingdomEventService extends KingdomContentService {

	constructor(
		private userProfileService: KingdomUserService,
		private characterService: KingdomCharacterService,
		private itemService: KingdomItemService,
		protected actionHandler: KingdomActionHandler,
		private schemaManager: SakuraSchemaManager
	) {
		super(actionHandler);
		this._services = new Hashmap<KingdomContentService>();
		this._services.put("kingdomUserProfile", this.userProfileService);
		this._services.put("kingdomCharacter", this.characterService);
	}

	buildValidator(target: any): SakuraValidatorBasic {
		return SakuraEntityValidationProxy.isArray(target);
	}

	set stageApiProxy(apiProxy: ApiProxyComponent) {
		this._stageApiProxy = apiProxy;
	}

	get stageApiProxy(): ApiProxyComponent {
		return this._stageApiProxy;
	}

	handle(): EntityBasic {
		var items = [];
		var events = this.entityStorage.get("kingdomEventGlobal");
		for(var i in events) {
			items.push(this.buildEventEntity(events[i]));
		}
		return items;
	}

	confirm(entity: KingdomEventBasic) {
		var event = new AgarthaEventBasic('kingdom-event-confirm', this, 'getItems');
		event.eventData = {
			'form': {
				'eventId': entity.eventId
			}
		};
		this._stageApiProxy.invokeNow(event, 'postItems', () => {});
	}

	buildQuestEntity(item: any): KingdomQuestEntity {
		if(item) {
			var quest = new KingdomQuestEntity(item['questId']);
			quest.requireLevel = item['requireLevel'];
			quest.rewardNumber = item['rewardNumber'];
			quest.reward1 = item['reward1'];
			quest.reward2 = item['reward2'];
			return quest;
		}
		return null;
	}

	buildEventEntity(item: any): KingdomEventBasic {
		var event = new KingdomEventBasic(item['eventId'], this);
		event.type = item['eventType'];
		event.title = item['eventTitle'];
		event.quests = item['quest'];
		event.offerId = item['offerId'];
		event.definition = this.schemaManager.loadDefinition("kingdom-event", item['definitionId']);
		event.schema = this.schemaManager.loadSchema("kingdom-content-event", item['definitionId']);
		return event;
	}
}