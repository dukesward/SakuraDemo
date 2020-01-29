import { Directive } from "@angular/core";

@Directive({
    selector: "[kingdomPubOffer]"
})
export class KingdomPublicOfferDirective {
    
    constructor() {
        console.log('kingdom pub offer directive init');
    }
}