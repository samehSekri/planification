import { Routes, RouterModule } from '@angular/router';

import { AdministrationComponent } from './administration.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { UserDetailsComponent } from "app/pages/administration/components/user-management/components/user-details";
import { RoleGuard } from "app/authentication/guard";
import { HabilitationManagementComponent } from 'app/pages/administration/components/habilitation-management/habilitation-management.component';
import { UnitManagementComponent } from 'app/pages/administration/components/unit-management/unit-management.component';
import { OperatorManagementComponent } from 'app/pages/administration/components/operator-management/operator-management.component';
import { ArticleManagementComponent } from 'app/pages/administration/components/article-management/article-management.component';


// noinspection TypeScriptValidateTypes
const routes: Routes = [
  {
    path: '',
    component: AdministrationComponent,
    children: [
      {
        path: 'user-management',
        component: UserManagementComponent,
        data: {
          pageTitle: 'general.menu.userManagement',
        }
      },
      {
        path: 'user-management/:id',
        component: UserDetailsComponent,
        data: {
          pageTitle: 'general.menu.userManagement'
        }
      },
      {
        path: 'habilitation-management',
        component: HabilitationManagementComponent,
        data: {
          pageTitle: 'general.menu.habilitationManagement',
        }
      },
      {
        path: 'unit-management',
        component: UnitManagementComponent,
        data: {
          pageTitle: 'general.menu.unitManagement',
        }
      },
      {
        path: 'operator-management',
        component: OperatorManagementComponent,
        data: {
          pageTitle: 'general.menu.operatorManagement',
        }
      },
      {
        path: 'article-management',
        component: ArticleManagementComponent,
        data: {
          pageTitle: 'general.menu.articleManagement',
        }
      }


    ]}
  
];

export const routing = RouterModule.forChild(routes);
