import { ApiProxyComponent } from './../../../common/api-proxy/api-proxy.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AgarthaApiManager } from 'src/app/agartha.api.manager';
import { EntityBasic } from 'src/app/models/entity.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { SakuraApiManager } from 'src/app/sakura.api.manager';
import { AgarthaApiScout } from 'src/app/agartha.api.scout';
import { AgarthaEventBasic } from 'src/app/sakura/agartha/entities/utility/agartha.event';

@Component({
  selector: 'stage',
  templateUrl: './stage.component.html',
  styleUrls: ['./stage.component.scss']
})
export class StageComponent implements OnInit {
  private singer: any;
  private actors: EntityBasic[];
  private scouts: AgarthaApiScout[];
  private apiManager: SakuraApiManager;
  private showStarted: boolean;

  @ViewChild(ApiProxyComponent) apiProxy: ApiProxyComponent;

  constructor() {
    this.actors = [];
    this.scouts = [];
    this.showStarted = false;
  }

  ngOnInit() {
    console.log("stage on init");
  }

  started(): boolean {
    return this.showStarted;
  }

  getApiProxy(): ApiProxyComponent {
    return this.apiProxy;
  }

  prepareEvents(moduleId: string, events: Hashmap<AgarthaEventBasic>) {
    this.apiProxy.setEvents(events);
    this.hireScouts(moduleId, events);
  }

  beforeShow(apiManager: SakuraApiManager) {
    this.apiManager = apiManager;
    this.apiProxy.bind(apiManager).error(data => data.message);
  }

  hireScouts(moduleId: string, events: Hashmap<AgarthaEventBasic>) {
    var filteredEvents: AgarthaEventBasic[] = events.toList().filter(event => event.isScout());
    for(var i=0; i<filteredEvents.length; i++) {
      this.scouts.push(new AgarthaApiScout(this.apiProxy, filteredEvents[i], 'getItems'));
    }
  }

  refresh(moduleId: string) {
    console.log(moduleId);
  }

  startShow(singer: any) {
    this.singer = singer;
    this.showStarted = true;
    this.planShow(this.apiManager.getApiSource(singer.module), () => {
      if(singer.handler) {
        return singer.handler.handle();
      }
    });
  }

  planShow(module: string, service: CallableFunction) {
    var self = this;
    this.apiProxy.registerScouts(this.scouts);
    this.apiProxy.invoke('getItems', () => {
      self.actors = service();
    });
    for(var i=0; i<this.scouts.length; i++) {
      // this.scouts[i].scout(5, () => {self.actors = service();});
    }
  }

}
