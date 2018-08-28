import { NgModule }      from '@angular/core';
import { CommonModule }  from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../../theme/nga.module';

import { AppTranslationModule } from '../../app.translation.module';
import { ErrorComponent } from './error.component';
import { routing } from '../../app.routing';


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
