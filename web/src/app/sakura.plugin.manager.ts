import { Injectable } from '@angular/core';
import { SakuraPluginBasic } from './models/utility/plugin.basic';
import { TypedList } from './models/data-structure/typed-list.model';
import { HttpServicePlugin } from './sakura/plugins/http.service.plugin';

@Injectable({
    providedIn: "root"
})
export class SakuraPluginManager {

    private plugins: TypedList<SakuraPluginBasic>;

    constructor() {
        this.plugins = new TypedList<SakuraPluginBasic>();
        this.plugins.add(new HttpServicePlugin());
    }

    addPlugin(plugin: SakuraPluginBasic) {
        this.plugins.add(plugin);
    }

    getPlugin(key: string): SakuraPluginBasic {
        var filtered: SakuraPluginBasic[] = this.plugins.filter((item) => item.matchPluginId(key));
        if(filtered.length > 0) {
            return filtered[0];
        }
        return null;
    }
}