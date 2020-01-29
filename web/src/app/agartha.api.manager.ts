import { Injectable } from "@angular/core";
import { SakuraApiManager } from './sakura.api.manager';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
    providedIn: "root"
})
export class AgarthaApiManager extends SakuraApiManager {

    protected domain = environment.productDomains.agartha;
    protected appKey = "agartha";
    protected apiSources = {};

    constructor(protected httpClient: HttpClient) {
        super(httpClient);
        this.endpoint = this.assembleEndpoint();
        this.apiKey = "admin";
    }

    doGetItems(actionId: string) {
        return this.doGet({}, actionId || "");
    }
}