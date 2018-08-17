import { NgModule }      from '@angular/core';
import { CommonModule }  from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../../theme/nga.module';

import { AppTranslationModule } from 'app/app.translation.module';
import { routing } from 'app/pages/gestion-scenario/gestion-scenario.routing';
import { ErrorComponent } from 'app/pages/error/error.component';


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AppTranslationModule,
    FormsModule,
    NgaModule,
    routing
  ],
  declarations: [
    ErrorComponent
  ]
})
export class ErrorModule {}
