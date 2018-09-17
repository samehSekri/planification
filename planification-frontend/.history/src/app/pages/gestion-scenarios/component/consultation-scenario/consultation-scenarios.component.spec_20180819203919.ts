import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionScenariosComponent } from './gestion-scenarios.component';

describe('GestionScenariosComponent', () => {
  let component: GestionScenariosComponent;
  let fixture: ComponentFixture<GestionScenariosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionScenariosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionScenariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
