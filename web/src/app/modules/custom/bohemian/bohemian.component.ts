import { ApiProxyComponent } from 'src/app/modules/common/api-proxy/api-proxy.component';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { Bohemian } from './entities/bohemian';
import { StageComponent } from './stage/stage.component';
import { SakuraService } from 'src/app/sakura.service';
import { SakuraApiManager } from 'src/app/sakura.api.manager';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { AgarthaEventBasic } from 'src/app/sakura/agartha/entities/utility/agartha.event';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';

@Component({
  selector: 'bohemians',
  templateUrl: './bohemian.component.html',
  styleUrls: ['./bohemian.component.scss']
})
export class BohemianComponent implements OnInit {

  private services: { [serviceId: string]: SakuraService };
  private events: any;
  private apiManager: SakuraApiManager;
  private schemaManager: SakuraSchemaManager;

  @Input() menus: Bohemian[];
  @Input() boards: Bohemian[];
  @Output() singer = new EventEmitter<object>();

  @ViewChild(StageComponent) stage;

  constructor() {
    this.menus = [];
    this.services = {};
    this.events = {};
  }

  ngOnInit() {
    // console.log("bohemian on init");
  }

  getStageApiProxy(): ApiProxyComponent {
    return this.stage.getApiProxy();
  }

  addMenu(menu: Bohemian) {
    menu.setSchema(this.schemaManager.loadSchema(menu.menuId, "bohemian-menu"));
    this.menus.push(menu);
  }

  registerApi(apiManager: SakuraApiManager, schemaManager: SakuraSchemaManager) {
    this.apiManager = apiManager;
    this.schemaManager = schemaManager;
    this.stage.beforeShow(this.apiManager);
  }

  registerService(key: string, service: SakuraService) {
    this.services[key] = service;
  }

  registerEvent(moduleId: string, event: AgarthaEventBasic) {
    if(!this.events[moduleId]) {
      this.events[moduleId] = new Hashmap<AgarthaEventBasic>();
    }
    this.events[moduleId].put(event.eventId, event);
  }

  notify(moduleId: string) {
    console.log("module [" + moduleId + "] has been handled");
    if(this.events[moduleId]) {
      this.stage.prepareEvents(moduleId, this.events[moduleId]);
    }
    if(this.apiManager) {
      if(this.stage.started()) {
        this.stage.refresh(moduleId);
      }else {
        this.stage.startShow({
          module: moduleId,
          component: "bohemian",
          handler: this.services[moduleId]
        });
      }
    }else {
      console.error("API not registered");
    }
  }

}
