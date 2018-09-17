
import { Routes, RouterModule } from '@angular/router';
import { GestionFichiersComponent } from '../gestion-fichiers/gestion-fichiers.component';
import { PolyvalenceFichierComponent } from 'app/pages/gestion-fichiers/components/polyvalence-fichier/polyvalence-fichier.component';
import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { CadenceClientComponent } from './components/cadence-client/cadence-client.component';
import { PolyvalenceFormComponent } from './components/polyvalence-fichier/polyvalence-form/polyvalence-form.component';


const routes: Routes = [
    {
        path: '',
        component: GestionFichiersComponent,
        children: [
            {
                path: 'fichier-polyvalence',
                component: PolyvalenceFormComponent,
                data: {
                    pageTitle: 'general.menu.polyvalenceForm',
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

