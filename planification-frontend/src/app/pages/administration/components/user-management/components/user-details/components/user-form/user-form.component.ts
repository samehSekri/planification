import { Component, OnInit, Input, Output } from '@angular/core';
import { EventEmitter } from "events";
import { FormsModule } from "@angular/forms";
import { TranslateService } from "@ngx-translate/core";
import { UserManagementService } from "app/pages/administration/components/user-management/user-management.service";
import { Message } from "primeng/primeng";
import { User } from "app/shared/model";
import { RoleNameEnum } from "app/shared/model/enumeration";
import { AuthenticationService } from "app/authentication";

@Component({
  selector: 'user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {
  @Input('value')
  public currentUser: User;
  public roleEnum: any[];
  public isSaving: boolean = false;
  public msgs: Message[] = [];
  public msgSuccess: Message[] = [];

  constructor(private translate: TranslateService,
    private userManagementService: UserManagementService,
    private authService: AuthenticationService,
  ) {
  }

  ngOnInit() {
    this.roleEnum = [];
    this.roleEnum.push({ label: '', value: null });
    this.roleEnum.push({ label: this.translate.instant('general.role.admin'), value: RoleNameEnum.ROLE_ADMIN });
    this.roleEnum.push({ label: this.translate.instant('general.role.user'), value: RoleNameEnum.ROLE_USER });
  }

  /**
   * Save user informations
   */
  public saveUser() {
    this.isSaving = false;
    this.msgs = [];
    this.msgSuccess = [];

    if (this.currentUser.id != null) {
      this.userManagementService.update(this.currentUser).subscribe(data => {
        //Update current user info stored on the localstorage when the user to update is the logged user.
        if (this.currentUser.id === this.authService.getCurrentUser().id) {
          this.authService.updateCurrentUser(data);
        }
        this.isSaving = true;

        this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
      }, error => {
        var bodyMsg = JSON.parse(error._body);
        if (error._body && bodyMsg) {
          this.msgs.push({ severity: 'error', summary: '', detail: bodyMsg.errors[0] });
        } else {
          this.msgs.push({ severity: 'error', summary: this.translate.instant("message.save.failedMsgTitle"), detail: this.translate.instant("message.save.failedMsg") });
        }
      });

    } else {
      this.userManagementService.add(this.currentUser).subscribe(response => {
        this.isSaving = true;
        this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
      
        //go to the next user
        this.currentUser = new User(null, "", null, null, null, null, [], true, new Date(), RoleNameEnum.ROLE_EMPTY, []);
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

  /**
   * function that check if the role name is selected
   * @return true if the empty role name selected
   */
  public isEmptyRoleChecked(): boolean {
    return (this.currentUser.roleName === RoleNameEnum.ROLE_EMPTY || this.currentUser.roleName === null);
  }
}
