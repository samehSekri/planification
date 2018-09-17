import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlghorithmeScenarioComponent } from './alghorithme-scenario.component';

describe('AlghorithmeScenarioComponent', () => {
  let component: AlghorithmeScenarioComponent;
  let fixture: ComponentFixture<AlghorithmeScenarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlghorithmeScenarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlghorithmeScenarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
