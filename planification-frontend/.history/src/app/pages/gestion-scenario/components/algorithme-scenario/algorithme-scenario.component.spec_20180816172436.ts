import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AlgorithmeScenarioComponent } from 'app/pages/gestion-scenario/components/algorithme-scenario/algorithme-scenario.component';


describe('AlghorithmeScenarioComponent', () => {
  let component: AlgorithmeScenarioComponent;
  let fixture: ComponentFixture<AlgorithmeScenarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlgorithmeScenarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlgorithmeScenarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
