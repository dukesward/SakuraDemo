import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetLoaderComponent } from './asset-loader.component';

describe('AssetLoaderComponent', () => {
  let component: AssetLoaderComponent;
  let fixture: ComponentFixture<AssetLoaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssetLoaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssetLoaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
