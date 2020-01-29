import { Directive } from "@angular/core";

@Directive({
    selector: "[kingdomCharacterCard]"
})
export class KingdomCharacterCardDirective {

    constructor() {
        console.log('kingdom character card directive init');
    }
}