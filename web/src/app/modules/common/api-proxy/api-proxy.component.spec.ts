import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiProxyComponent } from './api-proxy.component';

describe('ApiProxyComponent', () => {
  let component: ApiProxyComponent;
  let fixture: ComponentFixture<ApiProxyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApiProxyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApiProxyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
