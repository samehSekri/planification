import { NgModule }      from '@angular/core';
import { CommonModule }  from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../../theme/nga.module';

import { ErrorComponent } from "app/pages/error";
import { routing } from "app/pages/error/error.routing";
import { AppTranslationModule } from "app/app.translation.module";


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
