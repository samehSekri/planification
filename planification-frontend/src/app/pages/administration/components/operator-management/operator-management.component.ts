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
  msgs: any[];
  translate: any;
  unites: Unit[];
  uaps: Unit[];


  constructor(private operatorService: OperatorManagementService,private uniteService: UnitManagementService, private router: Router,
    private confirmationService: ConfirmationService,
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
  private getByParent(): void {
    this.uniteService.getByParent(this.currentUap).subscribe((data: Unit[]) => { this.unites = data; console.log(data); },
      error => console.log(error),
            () => console.log('Get all parents complete'));
  }
  private getCurrentUap():void{
    console.log(this.currentUap)


  }

  /**
   * function init
   */

  public saveUser() {
    this.isSaving = false;
    this.msgs = [];
    this.msgSuccess = [];

    if (this.currentOperator.matricule != null) {
     
      this.operatorService.add(this.currentOperator).subscribe(response => {
        this.isSaving = true;
        this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
      
        //go to the next Operator
      this.currentOperator = new Operator(null, "", "", "",null);
      },
        error => {
          var bodyMsg = JSON.parse(error._body);
          if (error._body && bodyMsg) {
            this.msgs.push({ severity: 'error', summary: '', detail: bodyMsg.errors[0] });
          } else {
            this.msgs.push({ severity: 'error', summary: 'Error Message', detail: 'Validation failed' });
          }
        });
    }
  }

  ngOnInit() {
    this.getAllOperators();       
    this.getUap();
    this.getByParent
  }
  
  public showDialog() {
    this.display = true;
  }
}