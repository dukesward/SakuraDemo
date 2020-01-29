import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { AgarthaServicePlugin } from '../../../services/service.plugin';

export class KingdomEventServicePlugin extends AgarthaServicePlugin {

    constructor() {
        super();
        this.pluginId.push("kingdom-event-global");
        this.pluginId.push("kingdom-event-confirm");
    }

    produceHttpHeaders(event: SakuraEventBasic) {
        var headers = {};
        headers['userProfileId'] = this.appStorage.getOne('kingdomUserProfile', 'userId');
        switch(event.eventId) {
            case "kingdom-event-global":
                headers['locationId'] = this.appStorage.getOne('kingdomUserProfile', 'locationId');
                break;
        }
        return headers;
    }

    producePostBody(event: SakuraEventBasic) {
        var body = event.eventData['form'] || {};
        return body;
    }
}