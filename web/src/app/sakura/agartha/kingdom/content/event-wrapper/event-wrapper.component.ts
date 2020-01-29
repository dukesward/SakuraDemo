import { KingdomCharacterCardDirective } from './../../templates/character-card.directive';
import { AgarthaEventBasic } from 'src/app/sakura/agartha/entities/utility/agartha.event';
import { SakuraKingdomApiManager } from 'src/app/sakura.kingdom.api.manager';
import { ApiProxyComponent } from './../../../../../modules/common/api-proxy/api-proxy.component';
import { KingdomPublicOfferDirective } from './../../templates/pub-offer.directive';
import { Component, ContentChild, TemplateRef, Input, ViewChild } from '@angular/core';
import { WhiteBoardComponent } from 'src/app/modules/common/white-board/white-board.component';
import { KingdomPublicAnnounceDirective } from '../../templates/pub-ann.directive';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { KingdomEventBasic } from '../../../entities/kingdom/event.global.entity';

@Component({
	selector: 'kingdom-event-wrapper',
	templateUrl: './event-wrapper.component.html',
	styleUrls: [
		'../../../../sakura.theme.scss',
		'../../kingdom.theme.scss',
		'./event-wrapper.component.scss'
	]
})
export class KingdomEventWrapperComponent extends WhiteBoardComponent {

	@Input() entity: KingdomEventBasic;
	@Input() events: AgarthaEventBasic[];

	@ContentChild(ApiProxyComponent) apiProxy: ApiProxyComponent;

	@ContentChild(KingdomPublicAnnounceDirective, {read: TemplateRef}) publicAnnounceTemplate;
	@ContentChild(KingdomPublicOfferDirective, {read: TemplateRef}) publicOfferTemplate;
	@ContentChild(KingdomCharacterCardDirective, {read: TemplateRef}) characterCardTemplate;

	constructor(
		protected schemaManager: SakuraSchemaManager,
		private apiManager: SakuraKingdomApiManager
	) {
		super(schemaManager);
		this.apiManager = apiManager;
	}

	showEvent(): boolean {
		return !this.entity.confirmed;
	}

	ngOnInit(): void {
		var self = this;
		this.moduleId = "kingdom-" + this.entity.type;
		if(this.events && this.events.length) {
			this.apiProxy
				.bind(this.apiManager)
				.error(data => data.message)
				.attachEvents(this.events)
				.invoke('getItems', () => {
					self.entity.dataReady = true;
				})
			;
		}
	}
}