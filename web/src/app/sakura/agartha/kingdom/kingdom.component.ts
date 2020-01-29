import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { BohemianComponent } from 'src/app/modules/custom/bohemian/bohemian.component';
import { TalentService } from './services/talent.service';
import { AgarthaKingdomApiManager } from 'src/app/agartha.kingdom.api.manager';
import { Bohemian } from 'src/app/modules/custom/bohemian/entities/bohemian';

@Component({
  selector: 'app-kingdom',
  templateUrl: './kingdom.component.html',
  styleUrls: [
    '../../sakura.theme.scss',
    '../agartha.component.scss',
    './kingdom.component.scss'
  ]
})
export class KingdomComponent implements OnInit {

  @ViewChild(BohemianComponent) bohemian;

  constructor(
    private elementRef: ElementRef,
    private talentService: TalentService,
    private apiManager: AgarthaKingdomApiManager
  ) {}

  ngOnInit() {
    this.registerServices();
    this.bohemian.addMenu(new Bohemian("kingdom-talent", "png"));
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = "#546d80";
  }

  registerServices() {
    this.bohemian.registerApi(this.apiManager);
    this.bohemian.registerService("talent", this.talentService);
  }

}
