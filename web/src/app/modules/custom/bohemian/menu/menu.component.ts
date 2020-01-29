import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Bohemian } from '../entities/bohemian';

@Component({
  selector: 'bohemian',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class BohemianMenuComponent implements OnInit {

  @Input() menuId: string;
  @Input() imgType: string;
  @Input() menuDisplayText: string;
  @Output() handler = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }

  handle() {
    this.handler.emit(this.menuId);
  }

}
