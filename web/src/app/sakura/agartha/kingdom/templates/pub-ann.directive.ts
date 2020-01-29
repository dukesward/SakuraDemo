import { Directive } from '@angular/core';

@Directive({
    selector: "[kingdomPubAnn]"
})
export class KingdomPublicAnnounceDirective {

    constructor() {
        console.log('kingdom pub ann directive init');
    }
}