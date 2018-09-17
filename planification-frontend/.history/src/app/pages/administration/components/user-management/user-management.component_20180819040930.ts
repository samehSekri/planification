import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from "app/authentication";
import { UserManagementService } from "app/pages/administration/components/user-management/user-management.service";
import { ConfirmationService } from "primeng/primeng";
import { TranslateService } from "@ngx-translate/core";
import { User } from "app/shared/model";
import { RoleNameEnum } from "app/shared/model/enumeration";



@Component({
  selector: 'user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss']
})
export class UserManagementComponent implements OnInit {
  public users: User[] = [];
  public roleEnum: any[];
  public currentUser: User;
  /**
   * The constructor of the class component
   * @param userService 
   * @param router 
   * @param confirmationService 
   * @param translate 
   * @param authenticationService 
   */
  constructor(private userService: UserManagementService, private router: Router,
    private confirmationService: ConfirmationService,
    private translate: TranslateService,
    private authenticationService: AuthenticationService) {
    //Set the current user
    this.currentUser = authenticationService.getCurrentUser();
  }

  /**
   * Get all users 
   */
  private getAllUsers(): void {
    this.userService
      .getAll()
      .subscribe((data: User[]) => { this.users = data; console.log(data); },
      error => console.log(error),
      () => console.log('Get all Items complete'));
  }

  /**
   * function init
   */
  ngOnInit() {

    this.roleEnum = [];
    this.roleEnum.push({ label: '', value: null });
    this.roleEnum.push({ label: 'general.role.admin', value: RoleNameEnum.ROLE_ADMIN });
    this.roleEnum.push({ label: 'general.role.user', value: RoleNameEnum.ROLE_USER });

    this.translateRoleName();
    this.getAllUsers();
  }

  /**
   * Desactivate a user
   * @param item : the current user to desactite
   * @type: User
   */
  public confirmDelete(item: User, dataTable: any) {
    this.confirmationService.confirm({
      message: this.translate.instant('message.confirmDeleteAction'),
      header: this.translate.instant('message.confirmDeleteDialogTitle'),
      icon: 'fa fa-trash',
      accept: () => {
        //Actual logic to perform a confirmation
        this.changeUserStatus(item);
      }
    });
  }

  /**
   * change the status of a user passed as param
   * @param item the user to change status
   * @type User
   */
  public changeUserStatus(item: User) {
    var index: number = this.users.indexOf(item);
    //Clone the users object into new list
    let listUsers = [...this.users];

    this.userService.changeUserStatus(item.id).subscribe(response => {
      //update list of users by replacing the returned user
      listUsers[index] = response;

      //Replace all list
      this.users = listUsers;
    });
  }

  /**
   * Translate RoleName Label for the current language
   */
  private translateRoleName() {
    this.translate.get('general.role.admin').subscribe(value => {
      this.roleEnum[1].label = value;
    });
    this.translate.get('general.role.user').subscribe(value => {
      this.roleEnum[2].label = value;
    });
  }

  /**
   * Translate the Role for the current language
   * @param roleName : roleName
   * @type RoleNameEnum
   * @returns a string that represent the translated roleName
   */
  public getRoleNameTranslation(roleName: RoleNameEnum): string {
    var result: string = '';

    if (roleName == RoleNameEnum.ROLE_ADMIN) {
      this.translate.get('general.role.admin').subscribe(value => {
        result = value;
      });
    } else if (roleName == RoleNameEnum.ROLE_USER) {
      this.translate.get('general.role.user').subscribe(value => {
        result = value;
      });
    }
    return result;
  }
}
