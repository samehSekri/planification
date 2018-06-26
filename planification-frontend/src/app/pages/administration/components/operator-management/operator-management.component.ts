import { Component, OnInit } from '@angular/core';
import { RoleNameEnum } from 'app/shared/model/enumeration';
import { Operator } from 'app/shared/model/operator.model';
import { OperatorManagementService } from 'app/pages/administration/components/operator-management/operator-management.service';
import { Router } from '@angular/router';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationService } from 'app/authentication';

@Component({
  selector: 'app-operator-management',
  providers: [OperatorManagementService],
  templateUrl: './operator-management.component.html',
  styleUrls: ['./operator-management.component.scss']
})
export class OperatorManagementComponent implements OnInit {
  public display: boolean = false;
  public operateurs: Operator[] = [];

  public currentOperator: Operator;

  constructor(private operatorService: OperatorManagementService, private router: Router,
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

  /**
   * function init
   */

  ngOnInit() {
    this.getAllOperators();       
  }
  
  public showDialog() {
    this.display = true;
  }
}