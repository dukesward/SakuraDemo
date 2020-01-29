import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { AgarthaServicePlugin } from '../../../services/service.plugin';

export class KingdomUserServicePlugin extends AgarthaServicePlugin {

    constructor() {
        super();
        this.pluginId.push("kingdom-user-profile");
    }

    produceHttpHeaders(event: SakuraEventBasic) {
        var headers = {}, initData: Hashmap<any> = event.init();
        headers['userProfileId'] = initData.get('userProfileId');
        return headers;
    }
}