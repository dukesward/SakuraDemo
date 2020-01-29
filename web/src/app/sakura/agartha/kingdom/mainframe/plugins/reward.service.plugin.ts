import { AgarthaServicePlugin } from '../../../services/service.plugin';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';

export class KingdomRewardServicePlugin extends AgarthaServicePlugin {
    
    constructor() {
        super();
        this.pluginId.push("kingdom-reward");
    }

    produceHttpHeaders(event: SakuraEventBasic) {
        var headers = {};
        headers['offerId'] = event.service.build(event.service).getOfferId();
        return headers;
    }
}