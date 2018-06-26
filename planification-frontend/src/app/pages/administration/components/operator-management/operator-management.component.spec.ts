import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperatorManagementComponent } from './operator-management.component';

describe('OperatorManagementComponent', () => {
  let component: OperatorManagementComponent;
  let fixture: ComponentFixture<OperatorManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperatorManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperatorManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
