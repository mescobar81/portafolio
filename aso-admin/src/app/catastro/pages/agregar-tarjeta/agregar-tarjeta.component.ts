import { Component } from '@angular/core';
import { CatastroService } from '../../services/catastro.service';

import { Usuario } from 'src/app/auth/interfaces/login.interfaces';

declare var Bancard:any;

export interface PeriodicElement {
  name: string;
  accion:string;
}
const ELEMENT_DATA: PeriodicElement[] = [];

@Component({
  selector: 'app-agregar-tarjeta',
  templateUrl: './agregar-tarjeta.component.html',
  styleUrls: ['./agregar-tarjeta.component.css']
})
export class AgregarTarjetaComponent {

  displayedColumns: string[] = ['name', 'accion'];

  dataSource = [...ELEMENT_DATA];

  constructor(private catastroService:CatastroService) { }

  ngAfterViewInit(): void {
    const existeUsuario = localStorage.getItem('usuario') || '';
    if(!existeUsuario){
      return;
    }

    const usuario:Usuario = JSON.parse(localStorage.getItem('usuario') || '') || null;
    this.catastroService.catastrarTarjeta(Number(usuario.nroSocio)).subscribe(resp => {
      console.log(resp);
      var styles = {
        'input-background-color' : '#453454',
        'input-text-color': '#B22222',
        'input-border-color' : '#CCCCCC',
        'input-placeholder-color' : '#999999',
        'button-background-color' : '#5CB85C',
        'button-text-color' : '#FFFFFF',
        'button-border-color' : '#4CAE4C',
        'form-background-color' : '#999999',
        'form-border-color' : '#DDDDDD',
        'header-background-color' : '#F5F5F5',
        'header-text-color' : '#333333',
        'hr-border-color' : '#B22222'
      };
  
      console.log('Process_id:', resp);

      Bancard.Cards.createForm('iframe-container', `${resp.process_id}`,
      styles);
    }, (err) => {
      console.log(err);
    });
  }
}
