import { Component, OnInit, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { Bohemian } from 'src/app/modules/custom/bohemian/entities/bohemian';
import { BohemianComponent } from 'src/app/modules/custom/bohemian/bohemian.component';
import { ModuleService } from './services/module.service';
import { AgarthaApiManager } from 'src/app/agartha.api.manager';

@Component({
  selector: 'app-agartha',
  templateUrl: './agartha.component.html',
  styleUrls: [
    '../sakura.theme.scss',
    './agartha.component.scss'
  ]
})
export class AgarthaComponent implements OnInit, AfterViewInit {

  @ViewChild(BohemianComponent) bohemian;

  constructor(
    private elementRef: ElementRef,
    private modulesService: ModuleService,
    private apiManager: AgarthaApiManager
  ) {}

  ngOnInit() {
    this.registerServices();
    this.bohemian.addMenu(new Bohemian("modules", "svg"));
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = "#546d80";
  }

  registerServices() {
    this.bohemian.registerApi(this.apiManager);
    this.bohemian.registerService("modules", this.modulesService);
  }
}
