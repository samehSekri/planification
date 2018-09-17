import { Component, OnInit } from '@angular/core';
import { UnitManagementService } from '../../administration/components/unit-management/unit-form.service';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { TranslateService } from '@ngx-translate/core';
import { UniteTypeEnum } from '../../../shared/model/enumeration/unitetype.enum';
import { Unit } from '../../../shared/model/unit.model';
import { Message } from 'primeng/primeng';
import { Article } from '../../../shared/model/article.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-gestion-scenarios',
  templateUrl: './gestion-scenarios.component.html',
  styleUrls: ['./gestion-scenarios.component.scss'],
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

  constructor(private uniteService: UnitManagementService,
) { }
  private getUap(): void {

    this.uniteService.getByType(UniteTypeEnum.UAP).subscribe((data: Unit[]) => { this.uaps = data; console.log(data); },
      error => console.log(error),
      () => console.log('Get all Items complete'));
  }
 
  ngOnInit() {
    this.getUap();
  //  this.currentArticle = new Article(null, null, null,null,null,null,null,null,null,true,null);
  }

}
