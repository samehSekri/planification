
import { Component, OnInit, Input } from '@angular/core';
import { RoleNameEnum, UniteTypeEnum } from 'app/shared/model/enumeration';
import { Operator } from 'app/shared/model/operator.model';
import { OperatorManagementService } from 'app/pages/administration/components/operator-management/operator-management.service';
import { Router } from '@angular/router';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationService } from 'app/authentication';
import { Message } from "primeng/primeng";
import { Unit } from '../../../../shared/model/unit.model';
import { UnitManagementService } from '../unit-management/unit-form.service';
import { ArticleManagementService } from 'app/pages/administration/components/article-management/article-management.service';
import { Article } from '../../../../shared/model/article.model';
@Component({
  selector: 'app-article-management',
  providers: [ArticleManagementService,UnitManagementService],
  templateUrl: './article-management.component.html',
  styleUrls: ['./article-management.component.scss']
})
export class ArticleManagementComponent implements OnInit {

  @Input('value')
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


  constructor(private articleService: ArticleManagementService,private uniteService: UnitManagementService, private router: Router,
    private confirmationService: ConfirmationService, private translate: TranslateService,
  ) { 
     
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
  private getAtelierByParent(): void {
    this.uniteService.getByParent(this.currentUap).subscribe((data: Unit[]) => { this.ateliers = data; console.log(data); },
      error => console.log(error),
            () => console.log('Get all parents complete'));
  }
  private getIlotByParent(): void {
    this.uniteService.getByParent(this.currentAtelier).subscribe((data: Unit[]) => { this.ilots = data; console.log(data); },
      error => console.log(error),
            () => console.log('Get all parents complete'));
  }
 
  /**
   * function init
   */

  public saveArticle() {
    this.isSaving = false;
    this.msgs = [];
    this.msgSuccess = [];
console.log(this.currentArticle);

     
      this.articleService.add(this.currentArticle).subscribe(response => {
        this.isSaving = true;
        this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
      
        //go to the next article
      this.currentArticle = new Article(null, null, null,null,null,null,null,null,null,true,null);
      },
        error => {
          var bodyMsg = JSON.parse(error._body);
          if (error._body && bodyMsg) {
            this.msgs.push({ severity: 'error', summary: '', detail: bodyMsg.errors[0] });
          } else {
            this.msgs.push({ severity: 'error', summary: 'Error Message', detail: 'Validation failed' });
          }
        });
        this.getAllArticles();
        this.closeDialog();

    //}
  }
  /**
   * change the status of a Article passed as param
   * @param item the Article to change status
   * @type Article
   */
  public changeArticleStatus(item: Article) {
    console.log(item.etat);
    this.articleService.changeArticleStatus(item.reference).subscribe(response => {
    this.getAllArticles();
   
    });
  }

  compareFn(c1: Article, c2: Article): boolean {
    return c1 && c2 ? c1.unite === c2.unite : c1 === c2;
   }
  editArticle(item){
console.log(item);

    this.showDialog();
    this.currentArticle=item;
  }
 

  ngOnInit() {
    this.getAllArticles();       
    this.getUap();
    this.currentArticle = new Article(null, null, null,null,null,null,null,null,null,true,true,null);

   
  }
  
  public showDialog() {
    this.display = true;
   
  }
  public closeDialog() {
    this.display = false;
  }

}
