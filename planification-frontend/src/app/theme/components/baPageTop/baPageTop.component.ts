import { AuthenticationService } from '../../../authentication/authentication.service';
import { Component, OnInit } from '@angular/core';

import { GlobalState } from '../../../global.state';
import { Router } from '@angular/router';
import { User } from "app/shared/model";
import { FormBuilder, Validators, FormGroup, AbstractControl } from "@angular/forms";
import { EqualPasswordsValidator, EmailValidator } from "app/theme/validators";
import { Message } from "primeng/primeng";
import { UserManagementService } from "app/pages/administration/components/user-management/user-management.service";
import { TranslateService } from "@ngx-translate/core";

@Component({
  selector: 'ba-page-top',
  templateUrl: './baPageTop.html',
  styleUrls: ['./baPageTop.scss']
})
export class BaPageTop implements OnInit {
  public hasRoleAdmin: boolean = false;

  public isScrolled: boolean = false;
  public isMenuCollapsed: boolean = false;
  public displayProfileDialog: boolean = false;
  public submitted: boolean = false;
  public form: FormGroup;
  public lastname: AbstractControl;
  public firstname: AbstractControl;
  public email: AbstractControl;
  public password: AbstractControl;
  public repeatPassword: AbstractControl;
  public passwords: FormGroup;
  public error: String;

  private currentUser: User;
  public msgSuccess: Message[] = [];

  constructor(private _state: GlobalState,
    private fb: FormBuilder,
    private auth: AuthenticationService, private router: Router,
    private userService: UserManagementService,
    private translate: TranslateService) {
    this._state.subscribe('menu.isCollapsed', (isCollapsed) => {
      this.isMenuCollapsed = isCollapsed;
    });

    this.hasRoleAdmin = auth.hasRoleAdmin();
    this.currentUser = auth.getCurrentUser();

    /*******************************************************
     * Create the profile form
     *******************************************************/
    this.form = fb.group({
      'lastname': ['', Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(50)])],
      'firstname': ['', Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(50)])],
      'email': ['', Validators.compose([Validators.required, EmailValidator.validate, Validators.maxLength(100)])],
      'passwords': fb.group({
        'password': ['', Validators.compose([Validators.minLength(5), Validators.maxLength(100)])],
        'repeatPassword': ['', Validators.compose([Validators.minLength(5), Validators.maxLength(100)])]
      }, { validator: EqualPasswordsValidator.validate('password', 'repeatPassword') })
    });

    this.lastname = this.form.controls['lastname'];
    this.firstname = this.form.controls['firstname'];
    this.email = this.form.controls['email'];
    this.passwords = <FormGroup>this.form.controls['passwords'];
    this.password = this.passwords.controls['password'];
    this.repeatPassword = this.passwords.controls['repeatPassword'];
  }

  ngOnInit(): void {
    this.error = "";
  }

  public toggleMenu() {
    this.isMenuCollapsed = !this.isMenuCollapsed;
    this._state.notifyDataChanged('menu.isCollapsed', this.isMenuCollapsed);
    return false;
  }

  public scrolledChanged(isScrolled) {
    this.isScrolled = isScrolled;
  }

  public openProfileDialog() {
    this.displayProfileDialog = true;
    this.lastname.setValue(this.currentUser.lastname);
    this.firstname.setValue(this.currentUser.firstname);
    this.email.setValue(this.currentUser.email);
    this.password.setValue("");
    this.repeatPassword.setValue("");
    return false;
  }

  public closeProfileDialog() {
    this.displayProfileDialog = false;
    return false;
  }

  public onSubmitProfileSettings(values: Object): void {
    this.error = "";
    this.submitted = true;
    if (this.form.valid) {
      this.currentUser.lastname = this.lastname.value;
      this.currentUser.firstname = this.firstname.value;
      this.currentUser.email = this.email.value;
      if (this.password.value) {
        this.currentUser.password = this.password.value;
      }
      this.userService.update(this.currentUser).subscribe(user => {
        this.auth.updateCurrentUser(user);
        this.msgSuccess.push(
          {
            severity: 'success',
            summary: this.translate.instant('message.save.successMsgTitle'),
            detail: this.translate.instant("message.save.successMsg")
          });
        this.displayProfileDialog = false;
      }, error => {
        var bodyMsg = JSON.parse(error._body);
        if (error._body && bodyMsg) {
          this.error = bodyMsg.errors[0];
        } else {
          this.error = error;
        }
      });
    }
  }
}
