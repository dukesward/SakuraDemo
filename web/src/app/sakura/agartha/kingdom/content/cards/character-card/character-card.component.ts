import { KingdomCharacterService } from './../../../mainframe/services/kingdom.character.service';
import { AgarthaEventBasic } from 'src/app/sakura/agartha/entities/utility/agartha.event';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { KingdomEventController } from './../../events/event-controller.component';
import { Component } from "@angular/core";
import { KingdomStatusContainer } from 'src/app/sakura/agartha/entities/kingdom/status.container';

@Component({
  templateUrl: "./character-card.component.html",
  styleUrls: [
		"../../../../../sakura.theme.scss",
		"../../../kingdom.theme.scss",
		"./character-card.component.scss"
	]
})
export class KingdomCharacterCard extends KingdomEventController {

	private character: any;
	private characterAttributes: any;
	private characterEquipments: any[];

	constructor(
		protected schemaManager: SakuraSchemaManager,
		private characterService: KingdomCharacterService
	) {
		super(schemaManager);
		this.characterEquipments = [];
	}

	ngOnInit() {
		this.entity.hook('dataReady', this.onDataReady.bind(this));
		var characters = this.entity.getAppData("kingdomCharacter");
		for(var i=0; i<characters.length; i++) {
			if(characters[i]['uuid'] === this.entity.eventId) {
				this.character = characters[i];
			}
		}
		// console.log(this.entity);
	}

	onDataReady(ready) {
		var entity = this.entity;
		if(ready) {
			var attributes = entity
				.getAppData("kingdomCharacterAttributes")
				.filter(item => item['characterId'] === entity.eventId);
			var inventoryEquipments = entity.getAppData("kingdomInventoryEquipments");
			if(attributes && attributes.length) {
				this.characterAttributes = attributes[0];
			}
			if(inventoryEquipments[entity.eventId]) {
				this.characterEquipments = inventoryEquipments[entity.eventId];
			}
		}
	}

	buildHpContainer() {
		if(this.characterAttributes) {
			return new KingdomStatusContainer("hp", this.characterAttributes.maxHp || 0);
		}
	}

	buildSpContainer() {
		if(this.characterAttributes) {
			return new KingdomStatusContainer(
				this.characterAttributes.spType,
				this.characterAttributes.maxSp || 0
			);
		}
	}

	buildCharacterHexaStatus() {
		var hexaStatusSchema = 
			this.schemaManager.loadDefinition("kingdom-character", "attributes");
		return hexaStatusSchema.filter(attr => attr.type === 'hexa');
	}

	buildCharacterAttributesEvent(): AgarthaEventBasic {
		var event = new AgarthaEventBasic(
			"kingdom-character-attributes", this.characterService, "getItems", this.entity);
		event.hook('finish', (eventId, target) => {
			if(eventId === 'kingdom-character-attributes') {
				var currentItems = this.entity.getAppData("kingdomCharacterAttributes");
				target = [target];
				if(currentItems) {
					for(var i=0; i<currentItems.length; i++) {
						if(currentItems[i]['characterId'] !== target['characterId']) {
							target.push(currentItems[i]);
						}
					}
				}
			}
			return target;
		});
		return event;
	}

	buildInventoryEquipmentsEvent(): AgarthaEventBasic {
		var event = new AgarthaEventBasic(
			"kingdom-inventory-equipments", this.characterService, "getItems", this.entity);
		var self = this;
		event.hook('finish', (eventId, target) => {
			if(eventId === 'kingdom-inventory-equipments') {
				var currentItems = this.entity.getAppData("kingdomInventoryEquipments") || {};
				var characterId = self.entity.eventId;
				currentItems[characterId] = target;
				return currentItems;
			}
		});
		return event;
	}

	serviceEvent(): AgarthaEventBasic[] {
		var events = [];
		events.push(this.buildCharacterAttributesEvent());
		events.push(this.buildInventoryEquipmentsEvent());
		return events;
	}
}