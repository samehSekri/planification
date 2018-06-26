import { AuthenticationService } from '../../authentication/authentication.service';

import { Component, OnInit, AfterViewInit, Renderer, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { RoleNameEnum } from "app/shared/model/enumeration";
import { User } from "app/shared/model";
import { UserManagementService } from "app/pages/administration/components/user-management/user-management.service";
import { Message } from "primeng/primeng";
import { TranslateService } from "@ngx-translate/core";

@Component({
  selector: 'login',
  templateUrl: './login.html',
  styleUrls: ['./login.scss']
})
export class LoginComponent implements OnInit, AfterViewInit {

  public form: FormGroup;
  public username: AbstractControl;
  public password: AbstractControl;
  public submitted: boolean = false;
  public error = '';
  public errorDialog = '';
  public errorShown: boolean;
  private currentUser: User;
  private role: any;
  public msg: Message[] = [];

  public formForgotPassword: FormGroup;
  public login: AbstractControl;
  public email: AbstractControl;
  public displayDialog: boolean = false;

  constructor(private fb: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    private auth: AuthenticationService,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private translate: TranslateService) {
    this.form = fb.group({
      'username': ['', Validators.compose([Validators.required, Validators.minLength(5)])],
      'password': ['', Validators.compose([Validators.required, Validators.minLength(5)])]
    });

    this.username = this.form.controls['username'];
    this.password = this.form.controls['password'];

    /**
     * Popup forgot login
     */
    this.formForgotPassword = fb.group({
      'username': ['', Validators.compose([Validators.required, Validators.minLength(5)])],
      'password': ['', Validators.compose([Validators.required, Validators.email])]
    });
    this.login = this.formForgotPassword.controls['username'];
    this.email = this.formForgotPassword.controls['password'];
    this.displayDialog = false;
  }


  public onSubmit(values: Object): void {
    this.submitted = true;

    if (this.form.valid) {
      this.auth.doLogin(values)
        .subscribe(
        data => {
          this.router.navigate(['/pages']);
        },
        error => {
          if (error.status == 0) {
            this.error = this.translate.instant("message.errConnectionRefused");
          } else {
            this.error = error.json().message;
          }
          this.errorShown = true;
        });
    }
  }

  public onSubmitPassword(values: Object): void {

    if (this.formForgotPassword.valid) {

      this.auth.resetPassword(values).subscribe(data => {
        if (data) {
          this.msg.push({ severity: 'success', summary: '', detail: this.translate.instant('message.email.resetPasswordSuccess') });
        }
        this.displayDialog = false;
      }, error => {
        var bodyMsg = JSON.parse(error._body);
        if (error._body && bodyMsg) {
          this.errorDialog = bodyMsg.errors[0];
        } else {
          this.errorDialog = error;
        }
      });
    }

  }

  onForgotPassword() {
    this.displayDialog = true;
    this.email.setValue("");
    this.login.setValue(this.username.value);
    this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#login'), 'focus', []);
    this.msg = [];
    this.error = "";
    this.errorDialog = "";
    return false;
  }
  ngOnInit(): void {
    this.auth.doLogout();
  }
  ngAfterViewInit(): void {
    this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#username'), 'focus', []);
  }
  onCloseDialog(): void {
    this.displayDialog = false;
  }

}
