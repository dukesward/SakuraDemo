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
  private events: Hashmap<SakuraEventBasic>;
  private apiManager: SakuraApiManager;
  private schemaManager: SakuraSchemaManager;

  @Input() menus: Bohemian[];
  @Input() boards: Bohemian[];
  @Output() singer = new EventEmitter<object>();

  @ViewChild(StageComponent) stage;

  constructor() {
    this.menus = [];
    this.services = {};
    this.events = new Hashmap<SakuraEventBasic>();
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
  }

  registerService(key: string, service: SakuraService) {
    this.services[key] = service;
  }

  registerEvent(event: SakuraEventBasic) {
    this.events.put(event.eventId, event);
    this.stage.beforeShow(this.events, this.apiManager);
  }

  notify(moduleId: string) {
    console.log("module [" + moduleId + "] has been handled");
    if(this.apiManager) {
      this.stage.startShow({
        module: moduleId,
        component: "bohemian",
        handler: this.services[moduleId]
      });
    }else {
      console.error("API not registered");
    }
  }

}
