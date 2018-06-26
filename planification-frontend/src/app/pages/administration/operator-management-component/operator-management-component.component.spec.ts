import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperatorManagementComponentComponent } from './operator-management-component.component';

describe('OperatorManagementComponentComponent', () => {
  let component: OperatorManagementComponentComponent;
  let fixture: ComponentFixture<OperatorManagementComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperatorManagementComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperatorManagementComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
