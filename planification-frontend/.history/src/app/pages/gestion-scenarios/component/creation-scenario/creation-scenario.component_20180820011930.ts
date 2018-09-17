import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { UniteTypeEnum } from '../../../../shared/model/enumeration/unitetype.enum';
import { Unit } from '../../../../shared/model/unit.model';
import { Message } from 'primeng/primeng';
import { Article } from '../../../../shared/model/article.model';
import { UnitManagementService } from '../../../administration/components/unit-management/unit-form.service';

@Component({
  selector: 'app-creation-scenario',
  templateUrl: './creation-scenario.component.html',
  styleUrls: ['./creation-scenario.component.scss'],
  providers: [UnitManagementService,ConfirmationService]
})
export class CreationScenarioComponent implements OnInit {
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

