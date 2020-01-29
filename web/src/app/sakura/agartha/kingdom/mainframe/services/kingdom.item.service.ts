import { KingdomActionHandler } from './kingdom.action.handler';
import { SchemaBasic } from 'src/app/models/schema.basic';
import { Hashmap } from './../../../../../models/data-structure/hashmap.model';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { EntityBasic } from './../../../../../models/entity.basic';
import { KingdomContentService } from './../../services/kingdom.content.service';
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class KingdomItemService extends KingdomContentService {

    private equipConfigs: any;
    private attrSchema: Hashmap<SchemaBasic>;

    constructor(
        private schemaManager: SakuraSchemaManager,
        protected actionHandler: KingdomActionHandler
    ) {
        super(actionHandler);
        this.equipConfigs = this.schemaManager.loadDefinition("kingdom-equipment");
        this.attrSchema = this.schemaManager.loadSchema("kingdom-equipment", "attributes");
    }

    store(key: string, target: EntityBasic): void {
        this.entityStorage.put(key + target['offerId'], target);
    }

    getOfferId(): string {
        return this._event.offerId;
    }

    buildTags(item: any): any[] {
        var tags = [];
        var typeConfigs = this.equipConfigs['type'];
        var subtypeConfigs = this.equipConfigs['subtype'];
        if(item.type) {
            tags.push({ name: typeConfigs[item.type], type: 'item-type' });
        }
        if(item.subtype) {
            tags.push({ name: subtypeConfigs[item.subtype], type: 'item-position' });
        }
        return tags;
    }

    buildItemPrice(price): any {
        var priceObject = {};
        price = parseInt(price) || 0;
        if(price < 100) {
            priceObject['copper'] = price;
        }else if(price < 10000) {
            priceObject['silver'] = Math.floor(price / 100);
            priceObject['copper'] = price - priceObject['silver'];
        }else {

        }
        return priceObject;
    }

    buildEquipAttributes(attrs: string): any[] {
        var output: any[] = [];
        var attrsSplit: string[] = attrs.split(',');
        for(var i=0; i<attrsSplit.length; i++) {
            output.push(this.buildAttrObject(attrsSplit[i]));
        }
        return output;
    }

    buildAttrObject(attr: string) {
        var attrObject = {};
        var keyVarPair = attr.split('=');
        var key = keyVarPair[0];
        var value = keyVarPair.length > 1 ? keyVarPair[1] : 0;
        var attrConfigs = this.equipConfigs['attributes'];
        for(var i=0; i<attrConfigs.length; i++) {
            var config = attrConfigs[i];
            if(config['synom'] === key) {
                var attrId = config['attribute'];
                var schema = this.attrSchema.get(attrId);
                attrObject['attrName'] = config['attributeName'];
                attrObject['attrValue'] = schema.translate({
                    value: value
                });
            }
        }
        return attrObject;
    }
}