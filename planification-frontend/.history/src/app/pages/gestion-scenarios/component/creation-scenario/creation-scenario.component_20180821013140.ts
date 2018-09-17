import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { UniteTypeEnum } from '../../../../shared/model/enumeration/unitetype.enum';
import { Unit } from '../../../../shared/model/unit.model';
import { Message } from 'primeng/primeng';
import { Article } from '../../../../shared/model/article.model';
import { UnitManagementService } from '../../../administration/components/unit-management/unit-form.service';
import { ArticleManagementService } from '../../../administration/components/article-management/article-management.service';
import { TranslateService } from '@ngx-translate/core';
import { OperatorManagementService } from '../../../administration/components/operator-management/operator-management.service';
import { Operator } from '../../../../shared/model/operator.model';

@Component({
  selector: 'app-creation-scenario',
  templateUrl: './creation-scenario.component.html',
  styleUrls: ['./creation-scenario.component.scss'],
  providers: [UnitManagementService,ConfirmationService,ArticleManagementService,OperatorManagementService]
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
  operateurs: Operator[];
  currentOperator: Operator;
  unite: any;

  constructor(private uniteService: UnitManagementService,private operatorService: OperatorManagementService,private articleService: ArticleManagementService, private translate: TranslateService
) { }
 
  ngOnInit() {
    this.getAllArticles();       
    this.currentArticle = new Article(null, null, null,null,null,null,null,null,null,true,null);
    this.getAllOperators();       
    this.getUap();
    this.currentOperator = new Operator(null, "", "", "",null);
  }
 /**
   * Get all articles 
   */
  private getAllArticles(): void {
    this.articleService.getAll().subscribe((data: Article[]) => { this.articles = data; console.log(data); },
      error => console.log(error),
      () => console.log('Get all Items complete'));
  }
 
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

public getOperateursByUnite(): void {

  this.uniteService.getByUnite(this.currentUnite).subscribe((data: Operator[]) => {
      this.operateurs = data;
      console.log(data);
  },
      error => console.log(error),
      () => console.log('Get all parents complete'));
}

/**
   * Get all operators 
   */
  private getAllOperators(): void {
    this.operatorService.getAll().subscribe((data: Operator[]) => { this.operateurs = data; console.log(data); },
      error => console.log(error),
      () => console.log('Get all Items complete'));
  }
}