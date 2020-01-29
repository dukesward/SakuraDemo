import { KingdomRewardServicePlugin } from './sakura/agartha/kingdom/mainframe/plugins/reward.service.plugin';
import { Injectable } from "@angular/core";
import { SakuraApiManager } from './sakura.api.manager';
import { HttpClient } from '@angular/common/http';
import { SakuraPluginManager } from './sakura.plugin.manager';
import { SakuraEventBasic } from './models/utility/event.basic';
import { KingdomUserServicePlugin } from './sakura/agartha/kingdom/mainframe/plugins/user.services.plugin';
import { KingdomCharacterServicePlugin } from './sakura/agartha/kingdom/mainframe/plugins/character.service.plugin';
import { KingdomEventServicePlugin } from './sakura/agartha/kingdom/mainframe/plugins/event.global.service.plugin';
import { KingdomLocationServicePlugin } from './sakura/agartha/kingdom/mainframe/plugins/location.service.plugin';

@Injectable({
    providedIn: "root"
})
export class SakuraKingdomApiManager extends SakuraApiManager {

    constructor(
        protected httpClient: HttpClient,
        private pluginManager: SakuraPluginManager
    ) {
        super(httpClient);
        this.endpoint = this.assembleEndpoint();
        this.apiSources = {
            "kingdom-user-profile": "user/profile",
            "kingdom-location": "user/location",
            "kingdom-character": "character/",
            "kingdom-character-attributes": "character/attributes",
            "kingdom-event-global": "event/global",
            "kingdom-event-confirm": "event/confirm",
            "kingdom-inventory": "item/inventory/all",
            "kingdom-inventory-equipments": "item/inventory/equipments",
            "kingdom-reward": "item/reward"
        };
        this.apiKey = "kingdom";
        this.pluginManager.addPlugin(new KingdomUserServicePlugin());
        this.pluginManager.addPlugin(new KingdomLocationServicePlugin());
        this.pluginManager.addPlugin(new KingdomCharacterServicePlugin());
        this.pluginManager.addPlugin(new KingdomEventServicePlugin());
        this.pluginManager.addPlugin(new KingdomRewardServicePlugin());
    }

    private getHttpHeaders(event: SakuraEventBasic) {
        this.headers = this.pluginManager.getPlugin(event.eventId).produce("http-headers", event);
    }

    doGetItems(event: SakuraEventBasic) {
        this.getHttpHeaders(event);
        var actionId = event.eventId;
        return this.doGet({}, this.apiSources[actionId] || actionId);
    }

    doPostItems(event: SakuraEventBasic) {
        this.getHttpHeaders(event);
        var actionId = event.eventId;
        var body = this.pluginManager.getPlugin(event.eventId).produce("post-body", event);
        return this.doPost({}, this.apiSources[actionId] || actionId, body);
    }
}