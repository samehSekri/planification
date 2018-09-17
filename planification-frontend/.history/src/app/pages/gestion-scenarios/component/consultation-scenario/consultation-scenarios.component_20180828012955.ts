import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { TranslateService } from '@ngx-translate/core';
import { Message } from 'primeng/primeng';
import { Router } from '@angular/router';
import { UnitManagementService } from '../../../administration/components/unit-management/unit-form.service';
import { Unit } from '../../../../shared/model/unit.model';
import { Article } from '../../../../shared/model/article.model';
import { UniteTypeEnum } from '../../../../shared/model/enumeration';
import { ConsultationScenarioService } from './consultation-scenarios.service';
import { Scenario } from '../../../../shared/model/scenario.model';

@Component({
  selector: 'app-consultation-scenario',
  templateUrl: './consultation-scenario.component.html',
  styleUrls: ['./consultation-scenario.component.scss'],
  providers: [UnitManagementService,ConfirmationService]
})
export class ConsulationScenarioComponent implements OnInit {
  public display: boolean = false;

  public isSaving: boolean = false;
  public currentUap:Unit;
  public currentAtelier:Unit;
  public  name:String;
  public currentUnite: Unit;
  public msgSuccess: Message[] = [];
 
  public msgs: Message[] = [];
 
  unites: Unit[];
  uaps: Unit[];
  ilots: Unit[];
  ateliers: Unit[];
  articles: Article[];
  currentArticle: Article;
  scenarios: Scenario[];

  constructor(private uniteService: UnitManagementService,private scenarioService:ConsultationScenarioService
) { }
public getUap(): void {

  this.uniteService.getByType(UniteTypeEnum.UAP).subscribe((data: Unit[]) => {
      this.uaps = data;
      if (this.uaps.length > 0) {
          this.currentUap = data[0];
          this.getAtelierByParent(this.currentUap);
      }
      console.log(data);
  },
      error => console.log(error),
      () => console.log('Get all Items complete'));
}
public getScenarioByUnite(): void {
console.log(this.currentUnite);

  this.scenarioService.getByUnite(this.currentUnite).subscribe((data: Scenario[]) => {
        
      console.log(data);
  },
      error => console.log(error),
      () => console.log('Get all Items complete'));
}

public getAtelierByParent(uap: Unit): void {
  this.currentUap = uap;

  this.uniteService.getByParent(this.currentUap).subscribe((data: Unit[]) => {
      this.ateliers = data;
      console.log(data);
  },
      error => console.log(error),
      () => console.log('Get all parents complete'));
}

public getIlotByParent(): void {
  this.uniteService.getByParent(this.currentAtelier).subscribe((data: Unit[]) => { this.ilots = data; console.log(data); },
      error => console.log(error),
      () => console.log('Get all parents complete'));
}


 
  ngOnInit() {
    this.getUap();
    
  //  this.currentArticle = new Article(null, null, null,null,null,null,null,null,null,true,null);
  }

}
