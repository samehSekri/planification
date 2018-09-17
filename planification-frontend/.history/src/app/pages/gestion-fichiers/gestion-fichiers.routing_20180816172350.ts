
import { Routes, RouterModule } from '@angular/router';

import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { CadenceClientComponent } from './components/cadence-client/cadence-client.component';
import { PolyvalenceFormComponent } from './components/polyvalence-fichier/polyvalence-form/polyvalence-form.component';
import { GestionFichiersComponent } from './gestion-fichiers.component';
import { PolyvalenceFichierComponent } from './components/polyvalence-fichier/polyvalence-fichier.component';


const routes: Routes = [
    {
        path: '',
        component: GestionFichiersComponent,
        children: [
            {
                path: 'fichier-polyvalence',
                component: PolyvalenceFichierComponent,
                data: {
                    pageTitle: 'general.menu.fichierPolyvalence',
                }
            },
            {
                path: 'cadence-client',
                component: CadenceClientComponent,
                data: {
                    pageTitle: 'general.menu.cadenceClient',
                }
            }]
    }

];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);

