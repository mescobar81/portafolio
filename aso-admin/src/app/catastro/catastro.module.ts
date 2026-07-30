import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CatastroRoutingModule } from './catastro-routing.module';
import { AgregarTarjetaComponent } from './pages/agregar-tarjeta/agregar-tarjeta.component';
import { MaterialModule } from '../material/material.module';
import { ViewTarjetaComponent } from './pages/view-tarjeta/view-tarjeta.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AgregarTarjetaComponent,
    ViewTarjetaComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    CatastroRoutingModule,
    MaterialModule
  ]
})
export class CatastroModule { }
