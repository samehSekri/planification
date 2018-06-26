import { Routes, RouterModule }  from '@angular/router';
import { ErrorComponent } from "app/pages/error";



// noinspection TypeScriptValidateTypes
const routes: Routes = [
  {
    path: '',
    component: ErrorComponent
  }
];

export const routing = RouterModule.forChild(routes);
