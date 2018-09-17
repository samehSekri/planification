

import { Routes, RouterModule } from '@angular/router';
import { GestionFichiersComponent } from '../gestion-fichiers/gestion-fichiers.component';
import { PolyvalenceFichierComponent } from 'app/pages/gestion-fichiers/components/polyvalence-fichier/polyvalence-fichier.component';
import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { GestionScenarioComponent } from './gestion-scenario.component';
import { AlghorithmeScenarioComponent } from './components/algorithme-scenario/alghorithme-scenario.component';


const routes: Routes = [
    {
        path: '',
        component: GestionScenarioComponent,
        children: [
            {
                path: 'algorithme-scenario',
                component: AlghorithmeScenarioComponent,
                data: {
                    pageTitle: 'general.menu.algorithmeScenario',
                }
            
            }]
    }

];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);

