import { Injectable } from "@angular/core";
import { ApiProxyComponent } from './modules/common/api-proxy/api-proxy.component';
import { AgarthaEventBasic } from './sakura/agartha/entities/utility/agartha.event';
import { SakuraApiScout } from './sakura.api.scout';
import { utils } from './common-utils';

export class AgarthaApiScout implements SakuraApiScout {

	private reqStatusPending: string = utils.getConstant('SAKURA:KINGDOM:REQSTATUS:PENDING');

	private apiProxy: ApiProxyComponent;
	private event: AgarthaEventBasic;
	private actionId: string;
	private lastTask: any;
	private moduleId: string;

	constructor(
		apiProxy: ApiProxyComponent,
		event: AgarthaEventBasic,
		actionId: string
	) {
		this.apiProxy = apiProxy;
		this.event = event;
		this.actionId = actionId;
		this.moduleId = "default";
	}

	scout(interval: number, callback: () => void) {
		if(callback) {
			this.lastTask = { interval: interval, task: callback };
			setTimeout(() => { this.triggerProxy(callback); }, interval * 1000);
		}
	}

	notify(data: any) {
		console.log(this.reqStatusPending);
		if(data.reqStatus === this.reqStatusPending) {
			if(this.lastTask) {
				this.scout(this.lastTask.interval, this.lastTask.task);
			}
		}
	}

	triggerProxy(callback: () => void) {
		this.apiProxy.addEvent(this.event);
		if(!this.apiProxy.isInvoked()) {
			this.apiProxy.invoke(this.actionId, callback || (() => {}));
		}
	}
}