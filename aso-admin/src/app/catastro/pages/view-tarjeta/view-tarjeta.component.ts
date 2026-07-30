import { Component, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

import { CatastroService } from '../../services/catastro.service';
import { Usuario } from 'src/app/auth/interfaces/login.interfaces';

@Component({
  selector: 'app-view-tarjeta',
  templateUrl: './view-tarjeta.component.html',
  styleUrls: ['./view-tarjeta.component.css']
})
export class ViewTarjetaComponent {


  displayedColumns: string[] = ['name'];
  ELEMENT_DATA:any[] = [];
  showSpinner:boolean = true;
  dataSource:any = [];
  usuario:Usuario = {};
  constructor(private catastroService:CatastroService,
              private route:Router) { }

  ngAfterViewInit() {
    if(!localStorage.getItem('usuario') || null){
      return;
    }
    this.usuario = JSON.parse(localStorage.getItem('usuario') || '') || null;

    this.catastroService.recuperarTarjeta(Number(this.usuario.nroSocio)).subscribe(data => {
      console.log('Recuperando Tarjeta: ', data);
      if(data.status === 'success') {
        this.showSpinner = false;
        this.dataSource = [...data.tarjetas];
      }else{
        /* Swal.fire({
          icon:'error',
          title:'Catastro Tarjeta',
          text: 'No tienes tarjeta aún. Quieres agregar una?',
          confirmButtonText:'Aceptar'
        });
        this.showSpinner = false; */
        Swal.fire({
          title: '¡En caso de que tenga deudas pendientes por sobregiro, autorizo suficientemente a la Aso a descontar de mi cuenta el importe total hasta la regularización de la misma!',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '¡Si, quiero!',
          cancelButtonText: 'Cancelar'
        }).then((result) => {
          if (result.isConfirmed) {
            this.showSpinner = false;
            this.route.navigateByUrl('/menu/agregar-tarjeta');
          }else{
            this.route.navigateByUrl('/menu');
          }
        });
      }
    }, (err) => {
      console.log(err); 
    });
  }

  agregarTarjeta(){
    this.route.navigateByUrl('/menu/agregar-tarjeta');
  }

  eliminarTarjeta(token:string):void{
  
    Swal.fire({
      title: '¿Estás seguro de eliminar la tarjeta?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '¡Si, eliminar!',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
      this.catastroService.eliminarTarjeta(token, Number(this.usuario.nroSocio))
        .subscribe(resp =>{

          if(resp.status === 'success'){
            const datosFiltrado:any[] = this.dataSource.filter((e:any) => e.alias_token !== token);
            this.dataSource = datosFiltrado;
            Swal.fire(
              '¡Eliminado!',
              resp.mensaje,
              'success'
            )
          }else{
            Swal.fire(
              'Atención!',
              resp.mensaje,
              'error'
            )
          }
        }, (err) =>{
          console.log(err);      
        });
      }
    });
  }
}
