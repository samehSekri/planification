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
@Component({
  selector: 'app-operator-management',
  providers: [OperatorManagementService,UnitManagementService],
  templateUrl: './operator-management.component.html',
  styleUrls: ['./operator-management.component.scss']
})
export class OperatorManagementComponent implements OnInit {
  @Input('value')
  public display: boolean = false;
  public operateurs: Operator[] = [];
  public isSaving: boolean = false;
  public currentUap:Unit;
  public currentAtelier:Unit;
  public  name:String;
  public currentUnite: Unit;
  public msgSuccess: Message[] = [];
  public currentOperator: Operator;
  public msgs: Message[] = []; 
  unites: Unit[];
  uaps: Unit[];
  ilots: Unit[];
  ateliers: Unit[];
  public messages: Message[];
  code: number;



  constructor(private operatorService: OperatorManagementService,private uniteService: UnitManagementService, private router: Router,
    private confirmationService: ConfirmationService, private translate: TranslateService,
  ) { 
     
  }

  /**
   * Get all operators 
   */
  private getAllOperators(): void {
    this.operatorService.getAll().subscribe((data: Operator[]) => { this.operateurs = data; console.log(data); },
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
public saveDialog(){
  this.code = 1
  this.showDialog();
}
  public saveOperator() {
    this.isSaving = false;
    this.msgs = [];
    this.msgSuccess = [];
    this.messages = [{ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") }];
   
console.log(this.currentOperator);
  //  if (this.currentOperator.matricule != null) {
     
      this.operatorService.add(this.currentOperator).subscribe(response => {
        this.isSaving = true;
        this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
      
        //go to the next Operator
      this.currentOperator = new Operator(null, "", "", "",null,true,null,null);
      },
      error => {
        var bodyMsg = JSON.parse(error._body);
        if (error._body && bodyMsg) {
            //  this.msgs.push({ severity: 'error', summary: '', detail: bodyMsg.message });
            this.messages = [{ severity: 'error', summary: 'Erreur', detail: bodyMsg.message }];
        } else {
            this.msgs.push({ severity: 'error', summary: 'Error Message', detail: 'Validation failed' });
        }
    });
    this.currentOperator = new Operator(null, "", "", "",null,true,null,null);
    this.currentAtelier=null;
    this.currentUap=null;
    this.getAllOperators();
        this.closeDialog();

    //}
  }
   public updateOperator(item) {

    this.isSaving = false;
    this.msgs = [];
    this.msgSuccess = [];
    this.messages = [{ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") }];
    
      this.operatorService.update(this.currentOperator).subscribe(response => {
        this.isSaving = true;
        this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
      
        //go to the next Operator
      this.currentOperator = new Operator(null, "", "", "",null,true,null,null);
      },
      error => {
        var bodyMsg = JSON.parse(error._body);
        if (error._body && bodyMsg) {
            //  this.msgs.push({ severity: 'error', summary: '', detail: bodyMsg.message });
            this.messages = [{ severity: 'error', summary: 'Erreur', detail: bodyMsg.message }];
        } else {
            this.msgs.push({ severity: 'error', summary: 'Error Message', detail: 'Validation failed' });
        }
    });
   
    this.getAllOperators();
        this.closeDialog();

    
  }
  compareFn(c1: Unit, c2: Unit): boolean {
    return c1 && c2 ? c1.name === c2.name : c1 === c2;
   }
  editOperator(item){
this.code=2;
    this.showDialog();
    this.currentOperator=item;
    if(this.currentOperator.unite){
    this.currentUnite = this.currentOperator.unite
    this.currentOperator.unite=this.currentUnite;
    this.currentAtelier=this.currentUnite.parent;
    this.currentUap=this.currentAtelier.parent;

    
}
  }
 /**
   * Delete a operator
   * @param item : the current operator to delete
   * @type: Operator
   */
  public confirmDelete(item: Operator, dataTable: any) {
    this.confirmationService.confirm({
      message: this.translate.instant('message.confirmDeleteAction'),
      header: this.translate.instant('message.confirmDeleteDialogTitle'),
      icon: 'fa fa-trash',
      accept: () => {
       console.log(item.matricule);
       
        
      this.operatorService.delete(item.matricule).subscribe(response => {
        this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.delete.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
      
         this.getAllOperators();
      },
        );
      
      }
    });

  }
  ngOnInit() {
    this.getAllOperators();       
    this.getUap();
    this.currentOperator = new Operator(null, "", "", "",null,true,null,null);
   
  }
  
  public showDialog() {
    this.currentOperator = new Operator(null, "", "", "",null,true,null,null);
    this.currentAtelier=null;
    this.currentUap=null;

    this.display = true;
   
  }
  public closeDialog() {
    this.display = false;
  }
}