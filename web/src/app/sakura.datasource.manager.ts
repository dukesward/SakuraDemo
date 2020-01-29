import { Injectable } from '@angular/core';
import { EntityBasic } from './models/entity.basic';
import { utils } from './common-utils';

@Injectable({
    providedIn: "root"
})
export class SakuraDataSourceManager {

    private dataSources: { [key:string]: any }

    constructor() {
        this.dataSources = {};
    }

    put(key: string, entity: any) {
        this.dataSources[utils.reformatCamel(key, '-')] = entity;
    }

    get(key: string) {
        return this.dataSources[key];
    }

    getOne(key: string, prop: string) {
        if(this.dataSources[key]) {
            return this.dataSources[key][prop];
        }else {
            throw {
                "status": "DataError",
                "message": "Data " + key + " not found"
            };
        }
    }
}