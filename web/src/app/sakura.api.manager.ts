import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SakuraApiManager {

  protected headers: string | { [name: string]: string | string[]; };
  protected domain = environment.productDomains.sakura;
  protected endpoint: string;
  protected appKey = "sakura";
  protected apiKey: string;
  protected dataType: string;
  protected apiSources = {};

  constructor(protected httpClient: HttpClient) {
    this.endpoint = this.assembleEndpoint();
    this.headers = {};
    this.dataType = "payload";
  }

  getServiceId(): string {
    return this.appKey + '.' + this.apiKey;
  }

  assembleEndpoint() {
    var endpoint = this.domain.endpoint;
    var protocol = endpoint.https ? 'https' : 'http';
    var port = endpoint.port ? (':' + endpoint.port) : '';
    return protocol + '://' + endpoint.host + port;
  }

  assembleParams(params: object) {

  }

  getApiSource(key: string) {
    return this.apiSources[key];
  }

  setDataType(type: string) {
    this.dataType = type;
    return this;
  }

  header(key: string, value: string) {
    this.headers[key] = value;
    return this;
  }

  doGet(params: object, actionKey: string) {
    var appKey = this.appKey || "sakura";
    if(!this.endpoint) {
      return throwError("Missing endpoint for target service");
    }else if(!this.apiKey) {
      return throwError("Missing api key for target service");
    }
    actionKey = actionKey || '';
    return this.httpClient.get(`${this.endpoint}/${appKey}/${this.apiKey}/${actionKey}`, { headers: new HttpHeaders(this.headers) });
  }

  doPost(params: object, actionKey: string, body: any) {
    var formData;
    var appKey = this.appKey || "sakura";
    if(!this.endpoint) {
      return throwError("Missing endpoint for target service");
    }else if(!this.apiKey) {
      return throwError("Missing api key for target service");
    }
    actionKey = actionKey || '';
    if(this.dataType == 'form') {
      formData = new FormData();
      for(var key in body) {
        formData.append(key, body[key]);
      }
    }else {
      formData = body;
    }
    return this.httpClient.post(`${this.endpoint}/${this.appKey}/${this.apiKey}/${actionKey}`, formData, { headers: new HttpHeaders(this.headers) });
  }
}
