

import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { GestionScenarioComponent } from './gestion-scenario.component';
import { AlgorithmeScenarioComponent } from './components/algorithme-scenario/algorithme-scenario.component';


const routes: Routes = [
    {
        path: 'gestion-scenario',
        component: GestionScenarioComponent,
        children: [
            {
                path: 'algorithme-senario',
                component: AlgorithmeScenarioComponent,
                data: {
                    pageTitle: 'general.menu.algorithmeScenario',
                }
            
            }]
    }

];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);

