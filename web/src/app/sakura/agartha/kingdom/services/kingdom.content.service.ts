import { ApiProxyComponent } from 'src/app/modules/common/api-proxy/api-proxy.component';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { KingdomActionHandler } from './../mainframe/services/kingdom.action.handler';
import { Injectable } from '@angular/core';
import { AgarthaServiceBasic } from '../../services/service.basic';
import { KingdomEventBasic } from '../../entities/kingdom/event.global.entity';

@Injectable({
  providedIn: 'root'
})
export class KingdomContentService extends AgarthaServiceBasic {

  protected _hasHeader: boolean;
  protected _event: KingdomEventBasic;
  protected _services: Hashmap<KingdomContentService>;
	protected _stageApiProxy: ApiProxyComponent;

  constructor(protected actionHandler: KingdomActionHandler) {
    super();
    this._hasHeader = false;
  }

  get hasHeader(): boolean {
    return this._hasHeader;
  }

  service(key: string) {
		var service = this._services.get(key);
		return service.build(service);
	}

  attachEvent(event: KingdomEventBasic) {
    this._event = event;
  }

  action(actionId: string, event: KingdomEventBasic) {
		this.actionHandler.build(this.actionHandler)[actionId + "Action"](event);
	}

  emptyItem(moduleDisplayName: string) {
    return {
			"moduleId": "empty-item",
			"moduleDisplayName": moduleDisplayName
    };
  }
}