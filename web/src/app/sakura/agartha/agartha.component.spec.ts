import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgarthaComponent } from './agartha.component';

describe('AgarthaComponent', () => {
  let component: AgarthaComponent;
  let fixture: ComponentFixture<AgarthaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgarthaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgarthaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
