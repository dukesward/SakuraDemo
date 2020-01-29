import { SakuraSchemaManager } from './../../../../../../sakura.schema.manager';
import { KingdomEventController } from './../../events/event-controller.component';
import { Component, Input } from "@angular/core";

@Component({
    selector: 'kingdom-character-base-card',
    templateUrl: "./character-base-card.component.html",
    styleUrls: [
        '../../../../../sakura.theme.scss',
        './character-base-card.component.scss'
    ]
})
export class KingdomCharacterBaseCard extends KingdomEventController {

    @Input() avatarIcon: string;
    @Input() characterEntity: any;
    @Input() hpContainer: any;
    @Input() spContainer: any;

    constructor(protected schemaManager: SakuraSchemaManager) {
        super(schemaManager);
    }

    ngOnInit() {}

    getCharacterAvartar() {
        // console.log(this.hpContainer);
        return 'characters/' + this.avatarIcon;
    }
}