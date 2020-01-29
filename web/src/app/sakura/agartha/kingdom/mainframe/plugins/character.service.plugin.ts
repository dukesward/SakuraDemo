import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { AgarthaServicePlugin } from '../../../services/service.plugin';

export class KingdomCharacterServicePlugin extends AgarthaServicePlugin {

    constructor() {
        super();
        this.pluginId.push("kingdom-character");
        this.pluginId.push("kingdom-character-attributes");
        this.pluginId.push("kingdom-inventory");
        this.pluginId.push("kingdom-inventory-equipments");
    }

    produceHttpHeaders(event: SakuraEventBasic) {
        var headers = {};
        switch(event.eventId) {
            case "kingdom-character":
                headers['userId'] = this.appStorage.getOne('kingdomUserProfile', 'userId');
                break;
            case "kingdom-character-attributes":
                headers['characterId'] = event.getEntityProperty('eventId');
                break;
            case "kingdom-inventory":
                headers['userProfileId'] = this.appStorage.getOne('kingdomUserProfile', 'userId');
                break;
            case "kingdom-inventory-equipments":
                headers['userProfileId'] = this.appStorage.getOne('kingdomUserProfile', 'userId');
                break;
        }
        return headers;
    }
}