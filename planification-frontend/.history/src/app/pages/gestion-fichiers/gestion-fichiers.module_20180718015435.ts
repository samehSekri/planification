import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../../theme/nga.module';

//import { routing } from './administration.routing';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { AppTranslationModule } from '../../app.translation.module';

import { HttpModule } from "@angular/http";

import { AuthenticationService } from "app/authentication";
import { Configuration } from "app/app.constants";
import { ConfirmDialogModule, ConfirmationService, DropdownModule, InputTextModule } from 'primeng/primeng';
import { RouterModule } from "@angular/router";
import { TranslateService } from '@ngx-translate/core';
import { MessagesModule } from 'primeng/primeng';

import { GrowlModule, DialogModule, InputMaskModule, SpinnerModule, TooltipModule, TreeModule, OrganizationChartModule } from 'primeng/primeng';
import { TieredMenuModule } from 'primeng/components/tieredmenu/tieredmenu';
import { SplitButtonModule } from 'primeng/components/splitbutton/splitbutton';
import { MenubarModule } from 'primeng/components/menubar/menubar';
import { ListboxModule } from 'primeng/components/listbox/listbox';
import { PolyvalenceFichierComponent } from './components/polyvalence-fichier/polyvalence-fichier.component';
import { GestionFichiersComponent } from './gestion-fichiers.component';
import { routing } from './gestion-fichiers.routing';
import { UserManagementService } from 'app/pages/administration/components/user-management/user-management.service';

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
    PolyvalenceFichierComponent,
    GestionFichiersComponent

  ],
  providers: [
    UserManagementService, AuthenticationService, ConfirmationService, TranslateService
  ]
})
export class GestionFichiersModule { }
