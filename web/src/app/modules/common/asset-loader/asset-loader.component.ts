import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'asset-loader',
  templateUrl: './asset-loader.component.html',
  styleUrls: ['./asset-loader.component.scss']
})
export class AssetLoaderComponent implements OnInit {

  protected assetType: string;

  constructor() {
  }

  ngOnInit() {
  }

}
