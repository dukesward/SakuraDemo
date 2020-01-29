import { ApiProxyComponent } from './../../../common/api-proxy/api-proxy.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AgarthaApiManager } from 'src/app/agartha.api.manager';
import { EntityBasic } from 'src/app/models/entity.basic';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { SakuraApiManager } from 'src/app/sakura.api.manager';

@Component({
  selector: 'stage',
  templateUrl: './stage.component.html',
  styleUrls: ['./stage.component.scss']
})
export class StageComponent implements OnInit {
  private singer: any;
  private actors: EntityBasic[];
  private apiManager: SakuraApiManager;

  @ViewChild(ApiProxyComponent) apiProxy: ApiProxyComponent;

  constructor() {
    this.actors = [];
  }

  ngOnInit() {
    console.log("stage on init");
  }

  getApiProxy(): ApiProxyComponent {
    return this.apiProxy;
  }

  beforeShow(events: Hashmap<SakuraEventBasic>, apiManager: SakuraApiManager) {
    this.apiProxy.setEvents(events);
    this.apiManager = apiManager;
    this.apiProxy.bind(apiManager).error(data => data.message);
  }

  startShow(singer: any) {
    this.singer = singer;
    this.planShow(this.apiManager.getApiSource(singer.module), () => {
      if(singer.handler) {
        return singer.handler.handle();
      }
    });
  }

  planShow(module: string, service: CallableFunction) {
    var self = this;
    this.apiProxy.invoke('getItems', () => {
      self.actors = service();
    });
  }

}
