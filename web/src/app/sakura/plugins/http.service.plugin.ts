import { SakuraPluginBasic } from 'src/app/models/utility/plugin.basic';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { utils } from 'src/app/common-utils';

export class HttpServicePlugin implements SakuraPluginBasic {

    protected pluginId: string[];

    constructor() {
        this.pluginId = ["http-service"];
    }

    matchPluginId(pluginId: string): boolean {
        return utils.inArray(this.pluginId, pluginId);
    }

    produce(key: string, event: SakuraEventBasic): any {
        var invokable = "produce" + utils.reformat(key, '-');
        if(this[invokable]) {
            return this[invokable](event);
        }
        return null;
    }
}