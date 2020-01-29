import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgarthaModuleComponent } from './agartha-module.component';

describe('AgarthaModuleComponent', () => {
  let component: AgarthaModuleComponent;
  let fixture: ComponentFixture<AgarthaModuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgarthaModuleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgarthaModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
