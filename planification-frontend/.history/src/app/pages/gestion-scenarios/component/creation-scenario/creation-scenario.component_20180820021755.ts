import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { UniteTypeEnum } from '../../../../shared/model/enumeration/unitetype.enum';
import { Unit } from '../../../../shared/model/unit.model';
import { Message } from 'primeng/primeng';
import { Article } from '../../../../shared/model/article.model';
import { UnitManagementService } from '../../../administration/components/unit-management/unit-form.service';
import { ArticleManagementService } from '../../../administration/components/article-management/article-management.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-creation-scenario',
  templateUrl: './creation-scenario.component.html',
  styleUrls: ['./creation-scenario.component.scss'],
  providers: [UnitManagementService,ConfirmationService,ArticleManagementService]
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

  constructor(private uniteService: UnitManagementService,private articleService: ArticleManagementService, private translate: TranslateService
) { }
 
  ngOnInit() {
    this.getUap();
  //  this.currentArticle = new Article(null, null, null,null,null,null,null,null,null,true,null);
  }
 /**
   * Get all articles 
   */
  private getAllArticles(): void {
    this.articleService.getAll().subscribe((data: Article[]) => { this.articles = data; console.log(data); },
      error => console.log(error),
      () => console.log('Get all Items complete'));
  }
  private getUap(): void {

    this.uniteService.getByType(UniteTypeEnum.UAP).subscribe((data: Unit[]) => { this.uaps = data; console.log(data); },
      error => console.log(error),
      () => console.log('Get all Items complete'));
 
 
}

