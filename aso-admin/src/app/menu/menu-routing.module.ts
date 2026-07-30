import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AgregarTarjetaComponent } from '../catastro/pages/agregar-tarjeta/agregar-tarjeta.component';
import { ViewTarjetaComponent } from '../catastro/pages/view-tarjeta/view-tarjeta.component';
import { MenuPageComponent } from './pages/menu-page/menu-page.component';
import { MensajeModalComponent } from '../shared/components/mensaje-modal/mensaje-modal.component';

const routes: Routes = [
  {
    path:'',
    component: MenuPageComponent,
    children: [
      {
        path: 'view-tarjeta',
        component: ViewTarjetaComponent
      },
      {
        path:'agregar-tarjeta',
        component: AgregarTarjetaComponent
      },
      {
        path:'mensaje-modal',
        component:MensajeModalComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenuRoutingModule { }
