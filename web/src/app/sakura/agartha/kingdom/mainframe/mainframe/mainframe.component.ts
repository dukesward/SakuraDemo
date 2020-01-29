import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { BohemianComponent } from 'src/app/modules/custom/bohemian/bohemian.component';
import { Bohemian } from 'src/app/modules/custom/bohemian/entities/bohemian';
import { SakuraKingdomApiManager } from 'src/app/sakura.kingdom.api.manager';
import { KingdomEventService } from '../services/kingdom.event.service';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { SakuraDataSourceManager } from 'src/app/sakura.datasource.manager';
import { ApiProxyComponent } from 'src/app/modules/common/api-proxy/api-proxy.component';
import { AgarthaEventBasic } from '../../../entities/utility/agartha.event';
import { KingdomUserService } from '../services/kingdom.user.service';
import { environment } from '../../../../../../environments/environment';
import { KingdomCharacterService } from '../services/kingdom.character.service';

@Component({
  selector: 'app-mainframe',
  templateUrl: './mainframe.component.html',
  styleUrls: [
    '../../../../sakura.theme.scss',
    '../../kingdom.theme.scss',
    './mainframe.component.scss'
  ]
})
export class MainframeComponent implements OnInit {

  @ViewChild(BohemianComponent) bohemian;
  @ViewChild(ApiProxyComponent) apiProxy;

  constructor(
    private elementRef: ElementRef,
    private eventService: KingdomEventService,
    private userService: KingdomUserService,
    private characterService: KingdomCharacterService,
    private apiManager: SakuraKingdomApiManager,
    private schemaManager: SakuraSchemaManager,
    private dataSourceManager: SakuraDataSourceManager
  ) {}

  ngOnInit() {
    this.initServices();
    this.apiProxy.bind(this.apiManager).error(data => data.message);
    this.initApiProxy();
    this.registerServices();
    this.registerEvents();
    this.bohemian.addMenu(new Bohemian("kingdom-event", "png"));
    this.bohemian.addMenu(new Bohemian("kingdom-character", "png"));
    this.bohemian.addMenu(new Bohemian("kingdom-quest", "png"));
    this.bohemian.addMenu(new Bohemian("kingdom-inventory", "png"));
  }

  getTheme() {
    return environment.profuctionContent.theme;
  }

  initApiProxy() {
    var self = this;
    this.apiProxy.attachEvent(new AgarthaEventBasic("kingdom-user-profile", this.userService, "getItems"));
    this.apiProxy.attachEvent(new AgarthaEventBasic("kingdom-character", this.characterService, "getItems"));
    this.apiProxy.attachEvent(new AgarthaEventBasic("kingdom-location", this.userService, "getItems"));
    this.apiProxy.invoke('getItems', () => {
      console.log("mainframe init data ready");
    });
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.background = "url('/assets/img/background-pattern-gold.png')";
  }

  initServices() {
    this.userService.prepare("userProfileId", "38576df2-1404-11ea-9302-704d7b4812f9");
  }

  registerServices() {
    this.eventService.stageApiProxy = this.bohemian.getStageApiProxy();
    this.bohemian.registerApi(this.apiManager, this.schemaManager);
    this.bohemian.registerService("kingdom-event", this.eventService);
    this.bohemian.registerService("kingdom-character", this.characterService);
  }

  registerEvents() {
    this.bohemian.registerEvent(new AgarthaEventBasic("kingdom-event-global", this.eventService, "getItems"));
  }

}
