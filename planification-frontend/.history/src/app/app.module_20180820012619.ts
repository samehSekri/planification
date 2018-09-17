import { NgModule, ApplicationRef } from '@angular/core';
//import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
/*
 * Platform and Environment providers/directives/pipes
 */
import { routing } from './app.routing';



// App is our top level component
import { App } from './app.component';
import { AppState, InternalStateType } from './app.service';
import { AuthenticationModule } from './authentication/authentication.module';
import { GlobalState } from './global.state';
import { LoginModule } from './pages/login/login.module';
import { NgaModule } from './theme/nga.module';
import { PagesModule } from './pages/pages.module';
import { Configuration } from "./app.constants";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Ng2CompleterModule } from "ng2-completer";
import { version } from 'punycode';
import { SlimLoadingBarModule } from 'ng2-slim-loading-bar';
import { CalendarModule } from 'primeng/primeng';


// Application wide providers
const APP_PROVIDERS = [
  AppState,
  Configuration,
  GlobalState
];

export type StoreType = {
  state: InternalStateType,
  restoreInputValues: () => void,
  disposeOldHosts: () => void
};

/**
 * `AppModule` is the main entry point into Angular2's bootstraping process
 */
@NgModule({
  bootstrap: [App],
  declarations: [
    App
  ],
  imports: [ // import Angular's modules
    BrowserAnimationsModule,
    HttpModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgaModule.forRoot(),
    Ng2CompleterModule,
    PagesModule,
    LoginModule,
    CalendarModule,

    AuthenticationModule,
    routing,
    SlimLoadingBarModule.forRoot(),
  ],
  providers: [ // expose our Services and Providers into Angular's dependency injection
    APP_PROVIDERS,
  ]
})

export class AppModule {

  constructor(public appState: AppState) {
  }
}
