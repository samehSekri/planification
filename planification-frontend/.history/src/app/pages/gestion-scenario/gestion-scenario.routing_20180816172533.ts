

import { Routes, RouterModule } from '@angular/router';
import { GestionFichiersComponent } from '../gestion-fichiers/gestion-fichiers.component';
import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { GestionScenarioComponent } from './gestion-scenario.component';
import { AlgorithmeScenarioComponent } from './components/algorithme-scenario/algorithme-scenario.component';


const routes: Routes = [
    {
        path: '',
        component: GestionScenarioComponent,
        children: [
            {
                path: 'algorithme-scenario',
                component: AlgorithmeScenarioComponent,
                data: {
                    pageTitle: 'general.menu.algorithmeScenario',
                }
            
            }]
    }

];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);

