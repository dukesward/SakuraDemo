import { Component, OnInit, Input } from '@angular/core';
import { AssetLoaderComponent } from '../asset-loader/asset-loader.component';

@Component({
  selector: 'img-loader',
  templateUrl: './img-loader.component.html',
  styleUrls: ['./img-loader.component.scss']
})
export class ImgLoaderComponent extends AssetLoaderComponent {

  @Input() imgSrc: string;
  @Input() imgType: string;
  @Input() imgHeight: string;
  @Input() imgSrcOnHover: string;

  private defaultImgSrc: string;

  constructor() {
    super();
  }

  get absUrl() {
    return window.location.href;
  }

  onHover() {
    if(this.imgSrcOnHover) {
      this.imgSrc = this.imgSrcOnHover;
    }
  }

  onLeave() {
    if(this.imgSrc != this.defaultImgSrc) this.imgSrc = this.defaultImgSrc;
  }

  ngOnInit() {
    this.defaultImgSrc = this.imgSrc;
    if(this.imgType === 'svg') {
      this.assetType = 'svg';
    }else if(this.imgType === 'png') {
      this.assetType = 'png';
    }else if(this.imgType === 'jpg') {
      this.assetType = 'jpg';
    }else if(this.imgType === 'gif') {
      this.assetType = 'gif';
    }else {
      this.assetType = 'img';
    }
  }

}
