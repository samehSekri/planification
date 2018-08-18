import { AuthenticationGuard } from '../authentication/guard/authentication.guard';
import { Routes, RouterModule } from '@angular/router';
import { Pages } from './pages.component';
import { ModuleWithProviders } from '@angular/core';
import { RoleGuard } from "../authentication/guard";

export const routes: Routes = [
  {
    path: 'login',
    loadChildren: 'app/pages/login/login.module#LoginModule'
  },
  {
    path:'404',
    loadChildren: 'app/pages/error/error.module#ErrorModule'
  },
  {
    path: 'pages',
    component: Pages,
    canActivate: [AuthenticationGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
      {
        path: 'administration',
        canActivate: [RoleGuard],
        data: { roles: ['ROLE_ADMIN'] },
        loadChildren: './administration/administration.module#AdministrationModule'
      },
      {
        path: 'gestion-fichiers',
        canActivate: [RoleGuard],
        data: { roles: ['ROLE_ADMIN'] },
        loadChildren: './gestion-fichiers/gestion-fichiers.module#GestionFichiersModule'
      },
      {
        path: 'gestion-scenario',
        canActivate: [RoleGuard],
        data: { roles: ['ROLE_ADMIN'] },
        loadChildren: './gestion-scenarios/gestion-scenarios.module#GestionScenariosModule'
      }
    ]
  }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);
