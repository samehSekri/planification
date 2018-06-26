import { AuthenticationGuard } from '../../authentication/guard/authentication.guard';
import { Dashboard } from '../dashboard/dashboard.component';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login.component';
import { ModuleWithProviders } from '@angular/core';
import { Register } from "app/pages/register";

// noinspection TypeScriptValidateTypes
export const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  { path: '**', redirectTo: '404' },
  { path: 'pages', redirectTo: 'pages/dashboard' }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);
