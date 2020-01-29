import { AgarthaServiceBasic } from './../../../services/service.basic';
import { KingdomEventBasic } from 'src/app/sakura/agartha/entities/kingdom/event.global.entity';
import { KingdomContentService } from './../../services/kingdom.content.service';
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class KingdomActionHandler extends AgarthaServiceBasic {

    constructor() {
        super();
    }

    confirmAction(event: KingdomEventBasic) {
        event.confirmed = true;
	}
}