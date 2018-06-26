import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HabilitationManagementComponent } from './habilitation-management.component';

describe('HabilitationManagementComponent', () => {
  let component: HabilitationManagementComponent;
  let fixture: ComponentFixture<HabilitationManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HabilitationManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HabilitationManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
