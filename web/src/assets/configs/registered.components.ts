import { KingdomCharacterCard } from './../../app/sakura/agartha/kingdom/content/cards/character-card/character-card.component';
import { KingdomPublicOffer } from './../../app/sakura/agartha/kingdom/content/events/pub-offer/pub-offer.component';
import { AgarthaModuleComponent } from 'src/app/sakura/agartha/components/agartha-module/agartha-module.component';
import { KingdomPublicAnnouncement } from 'src/app/sakura/agartha/kingdom/content/events/pub-anoun/pub-ann.component';

export default {
    "agartha-module": {
        "type": AgarthaModuleComponent
    },
    "kingdom-pub-ann": {
        "type": KingdomPublicAnnouncement
    },
    "kingdom-pub-offer": {
        "type": KingdomPublicOffer
    },
    "kingdom-character-card": {
        "type": KingdomCharacterCard
    }
}