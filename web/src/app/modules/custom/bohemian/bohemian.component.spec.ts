import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BohemianComponent } from './bohemian.component';

describe('BohemianComponent', () => {
  let component: BohemianComponent;
  let fixture: ComponentFixture<BohemianComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BohemianComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BohemianComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
