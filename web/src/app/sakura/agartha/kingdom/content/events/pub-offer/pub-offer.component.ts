import { utils } from 'src/app/common-utils';
import { KingdomEventController } from './../event-controller.component';
import { AgarthaEventBasic } from 'src/app/sakura/agartha/entities/utility/agartha.event';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { Component } from "@angular/core";
import { KingdomItemService } from '../../../mainframe/services/kingdom.item.service';

@Component({
	templateUrl: './pub-offer.component.html',
	styleUrls: [
		'../../../../../sakura.theme.scss',
		'../../../kingdom.theme.scss',
		'../../event-wrapper/event-wrapper.component.scss',
		'./pub-offer.component.scss'
	]
})
export class KingdomPublicOffer extends KingdomEventController {

	private offer: any;

	constructor(
		protected schemaManager: SakuraSchemaManager,
		private itemService: KingdomItemService
	) {
		super(schemaManager);
	}

	ngOnInit() {
		this.itemService.attachEvent(this.entity);
	}

	confirm() {
    	this.entity.action('confirm');
  	}

	getOfferType(): string {
		var definitions = this.schemaManager.loadDefinition("kingdom-event", "image-source");
		var offer = this.entity.getAppData("kingdomReward" + utils.reformatCamel(this.entity.offerId, '-'));
		var offerType = offer ? offer.offerType : '';
		return definitions ? definitions[offerType] : null;
	}

	getItemIcon(): string {
		if(!this.offer) {
			this.offer = this.entity.getAppData("kingdomReward" + utils.reformatCamel(this.entity.offerId, '-'));
		}
		var output = '';
		if(this.offer) {
			var reward = this.offer.reward;
			if(reward) {
				output = "items/" + reward.item.itemId;
			}
		}
		return output;
	}

	getItemInfo(): any {
		if(!this.offer) {
			this.offer = this.entity.getAppData("kingdomReward" + utils.reformatCamel(this.entity.offerId, '-'));
		}
		var output = '';
		if(this.offer) {
			var reward = this.offer.reward;
			if(reward) {
				output = reward.item.info;
				output['icon'] = this.getItemIcon();
				output['name'] = reward.item.name;
				output['quality'] = output['quality'].toLowerCase();
			}
		}
		return output;
	}

	getEventMessage(): string {
		return this.getContentSchema("message");
	}

	serviceEvent(): AgarthaEventBasic {
		return new AgarthaEventBasic("kingdom-reward", this.itemService, "getItems");
	}
}