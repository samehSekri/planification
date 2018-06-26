import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../../theme/nga.module';
import { AppTranslationModule } from '../../app.translation.module';
import { SharedModule } from '../../shared/shared.module';
import { routing } from './login.routing';

import { LoginComponent } from './login.component';
import { RouterModule } from '@angular/router';
import { DialogModule, MessagesModule, GrowlModule } from "primeng/primeng";
import { AuthenticationService } from "app/authentication";
import { TranslateService } from "@ngx-translate/core";


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    NgaModule,
    AppTranslationModule,
    MessagesModule,
    GrowlModule,
    DialogModule,
    RouterModule,
    SharedModule,
    routing
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    AuthenticationService, TranslateService
  ]
})
export class LoginModule  {}
