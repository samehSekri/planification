import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../../theme/nga.module';

import { routing } from './administration.routing';
import { AdministrationComponent } from './administration.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { AppTranslationModule } from '../../app.translation.module';

import { HttpModule } from "@angular/http";

import { AuthenticationService } from "app/authentication";
import { Configuration } from "app/app.constants";
import { UserManagementService } from './components/user-management/user-management.service';
import { ConfirmDialogModule, ConfirmationService, DropdownModule, InputTextModule } from 'primeng/primeng';
import { RouterModule } from "@angular/router";
import { UserDetailsComponent } from "app/pages/administration/components/user-management/components/user-details";
import { UserFormComponent } from "app/pages/administration/components/user-management/components/user-details/components/user-form";
import { TranslateService } from '@ngx-translate/core';
import { MessagesModule } from 'primeng/primeng';

import { GrowlModule, DialogModule, InputMaskModule, SpinnerModule, TooltipModule, TreeModule, OrganizationChartModule } from 'primeng/primeng';
import { HabilitationManagementComponent } from 'app/pages/administration/components/habilitation-management/habilitation-management.component';
import { UnitManagementComponent } from './components/unit-management/unit-management.component';
import { OperatorManagementComponent } from './components/operator-management/operator-management.component';
import { UnitFormComponent } from './components/unit-management/unit-form/unit-form.component';
import { TieredMenuModule } from 'primeng/components/tieredmenu/tieredmenu';
import { SplitButtonModule } from 'primeng/components/splitbutton/splitbutton';
import { MenubarModule } from 'primeng/components/menubar/menubar';
import { arrayToTree } from 'performant-array-to-tree'
import { ListboxModule } from 'primeng/components/listbox/listbox';
import { ArticleManagementComponent } from './components/article-management/article-management.component';
import { PolyvalenceFichierComponent } from '../gestion-fichiers/components/polyvalence-fichier/polyvalence-fichier.component';
import { GestionFichiersComponent } from '../gestion-fichiers/gestion-fichiers.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgaModule,
    DataTableModule,
    SharedModule,
    HttpModule,
    DropdownModule,
    MessagesModule,
    TooltipModule,
    InputTextModule,
    ConfirmDialogModule,
    DialogModule,
    GrowlModule,
    SpinnerModule,
    InputMaskModule,
    AppTranslationModule,
    RouterModule,
    routing,
    TreeModule,
    OrganizationChartModule,
    TieredMenuModule,
    SplitButtonModule,
    MenubarModule,
    ListboxModule

  ],
  declarations: [
    AdministrationComponent,
    UserManagementComponent,
    UserDetailsComponent,
    UserFormComponent,
    HabilitationManagementComponent,
    UnitManagementComponent,
    OperatorManagementComponent,
    UnitFormComponent,
    PolyvalenceFichierComponent,
    GestionFichiersComponent,
    ArticleManagementComponent,

  ],
  providers: [
    UserManagementService, AuthenticationService, ConfirmationService, TranslateService
  ]
})
export class AdministrationModule { }
