import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperatorFormComponentComponent } from './operator-form-component.component';

describe('OperatorFormComponentComponent', () => {
  let component: OperatorFormComponentComponent;
  let fixture: ComponentFixture<OperatorFormComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperatorFormComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperatorFormComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
