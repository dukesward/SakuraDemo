import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { BohemianComponent } from 'src/app/modules/custom/bohemian/bohemian.component';
import { SakuraApiManager } from 'src/app/sakura.api.manager';
import { ApiProxyComponent } from 'src/app/modules/common/api-proxy/api-proxy.component';
import { Bohemian } from 'src/app/modules/custom/bohemian/entities/bohemian';
import { environment } from '../../../../../../environments/environment';
import { SakuraSchemaManager } from 'src/app/sakura.schema.manager';
import { KingdomEventService } from '../services/kingdom.event.service';
import { AgarthaEventBasic } from '../../../entities/utility/agartha.event';

@Component({
	templateUrl: './forge.component.html',
	styleUrls: [
		'../../../../sakura.theme.scss',
		'../../kingdom.theme.scss',
		'./forge.component.scss'
	]
})
export class ForgeComponent implements OnInit {

	@ViewChild(BohemianComponent) bohemian;
	@ViewChild(ApiProxyComponent) apiProxy;

	constructor(
		private elementRef: ElementRef,
		private eventService: KingdomEventService,
		private apiManager: SakuraApiManager,
		private schemaManager: SakuraSchemaManager
	) {
		
	}

	ngOnInit(): void {
		this.apiProxy.bind(this.apiManager).error(data => data.message);
		this.registerServices();
		this.bohemian.addMenu(new Bohemian("kingdom-event", "png", this.getThemeShort()));
	}

	getThemeShort() {
		return environment.productionContent["theme-forge-short"];
	}

	getTheme() {
		return environment.productionContent["theme-forge"];
	}

	ngAfterViewInit() {
		this.elementRef.nativeElement.ownerDocument.body.style.background = "url('/assets/jpg/fantasy/dragon-yellow.jpg')";
		this.elementRef.nativeElement.ownerDocument.body.style["background-size"] = "cover";
  }

	registerServices() {
    this.bohemian.registerApi(this.apiManager, this.schemaManager);
    this.bohemian.registerService("kingdom-event", this.eventService);
	}
	
	registerEvents() {
		this.bohemian.registerEvent(new AgarthaEventBasic("kingdom-event-schema", this.eventService, "getItems"));
	}
    
}