import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { WhiteBoardComponent } from './../../../../../modules/common/white-board/white-board.component';
import { Component, Input } from "@angular/core";
import { KingdomItemService } from '../../mainframe/services/kingdom.item.service';

@Component({
  selector: 'kingdom-equip',
  templateUrl: './kingdom.equipment.html',
  styleUrls: [
    '../../../../sakura.theme.scss',
    '../../kingdom.theme.scss'
  ]
})
export class KingdomEquipmentComponent extends WhiteBoardComponent {
		
	@Input() item: any;

	constructor(
		protected schemaManager: SakuraSchemaManager,
		private itemService: KingdomItemService
	) {
		super(schemaManager);
	}

	ngOnInit(): void {}

	getAttributes() {
		if(this.item) {
			return this.itemService.buildEquipAttributes(this.item.attrs);
		}
	}

	getPrices() {
		if(this.item) {
			return this.itemService.buildItemPrice(this.item.price);
		}
	}

	getTags() {
		if(this.item) {
			return this.itemService.buildTags(this.item);
		}
	}
}