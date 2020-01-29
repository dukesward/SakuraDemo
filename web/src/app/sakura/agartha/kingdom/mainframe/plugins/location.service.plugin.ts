import { AgarthaServicePlugin } from '../../../services/service.plugin';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';

export class KingdomLocationServicePlugin extends AgarthaServicePlugin {

    constructor() {
        super();
        this.pluginId.push("kingdom-location");
    }

    produceHttpHeaders(event: SakuraEventBasic) {
        var headers = {};
        headers['locationId'] = this.appStorage.getOne('kingdomUserProfile', 'locationId');
        return headers;
    }
} 