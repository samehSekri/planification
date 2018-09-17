
import { Routes, RouterModule } from '@angular/router';
import { GestionFichiersComponent } from '../gestion-fichiers/gestion-fichiers.component';
import { PolyvalenceFichierComponent } from 'app/pages/gestion-fichiers/components/polyvalence-fichier/polyvalence-fichier.component';
import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';


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
            }]
    }

];


export const routing: ModuleWithProviders = RouterModule.forChild(routes);

