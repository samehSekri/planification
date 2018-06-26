import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppTranslationModule } from '../../app.translation.module';
import { NgaModule } from '../../theme/nga.module';

import { Dashboard } from './dashboard.component';
import { routing } from './dashboard.routing';

import { DataTableModule, SharedModule } from 'primeng/primeng';
import { DashboardService } from "app/pages/dashboard/dashboard.service";

import { AuthenticationService } from "app/authentication";
import { TranslateService } from "@ngx-translate/core";
import { TooltipModule } from 'primeng/components/tooltip/tooltip';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AppTranslationModule,
    NgaModule,
    routing,
    DataTableModule, SharedModule,
    TooltipModule

  ],
  declarations: [
    Dashboard
  ],
  providers: [
    DashboardService, AuthenticationService, TranslateService
  ]
})
export class DashboardModule { }
