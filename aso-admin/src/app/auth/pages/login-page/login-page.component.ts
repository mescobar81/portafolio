import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {MatDialog} from '@angular/material/dialog';

import * as CryptoJS from 'crypto-js';
import Swal from 'sweetalert2';

import { ICredencial } from '../../interfaces/credencial.interfaces';
import { AuthService } from '../../services/auth.service';
import { ILogin } from '../../interfaces/login.interfaces';
import { LoginDialogComponent } from '../../components/login-dialog/login-dialog.component';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  Correo:string = '';
  Celular:string = '';
  hide: boolean = true;
  myForm: FormGroup = this.fb.group({
    documento: ['', [Validators.required]],
    clave: ['', [Validators.required]],
  });
  constructor(public loginDialog:MatDialog,
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService) { }

  isValidCampos(campo: string): boolean | null {
    return this.myForm.controls[campo].errors && this.myForm.controls[campo].touched;
  }

  getErrorsMensaje(campo: string): string | null {
    if (!this.myForm.controls[campo].errors) {
      return null;
    }
    const errors = this.myForm.controls[campo].errors || {};
    for (const key of Object.keys(errors)) {
      switch (key) {
        case 'required':
          return 'Campo es requerido'
        default:
          return 'Campo inválido'
      }
    }
    return '';
  }

  login() {
  
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      return;
    }

    let { documento, clave } = this.myForm.value
    clave = CryptoJS.SHA256(clave).toString(CryptoJS.enc.Hex);
    const credencial: ICredencial = {
      documento: documento,
      clave: clave,
      device: {
        os: '',
        ip: '',
        model: '',
        version: ''
      }
    };

    this.authService.login(credencial).subscribe((resp:ILogin) => {

      if(resp.usuario?.valido && resp.usuario.Celular != '' && resp.usuario.Correo != ''){
        this.router.navigateByUrl('/menu');
      }else if(resp.usuario?.Celular != '' && resp.usuario?.Correo != ''){
        /*
        credenciales inválidas
        */
        Swal.fire({
          icon:'error',
          title: 'Aso Tigo',
          text: resp.usuario?.mensaje
        });
      }else{
        Swal.fire({
          title: '¿Quieres actualizar tus datos?',
          text: `Datos de ${resp.usuario.mensaje}`,
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '¡Si, actualizar!',
          cancelButtonText:'Cancelar'
        }).then((result) => {
          if (result.isConfirmed) {
            const dialogRef = this.loginDialog.open(LoginDialogComponent, {
              data:{
                correo:this.Correo, 
                celular: this.Celular
              }
            });
            dialogRef.afterClosed().subscribe(data =>{
              /*
                si el usuario cerró el dialogo sin ingresar datos
              */
              if(!data){
                return;
              }
              const {correo, celular} = data;
              const usuario = JSON.parse(localStorage.getItem('usuario') || '');
              const params = {
                nroSocio:usuario.nroSocio,
                Celular:celular,
                Correo:correo,
              }

              this.authService.actualizarDatoAdicionalUsuario(params).subscribe(
                resp =>{
                  if(resp.status === 'success'){
                    Swal.fire({
                      icon:'success',
                      title: 'Aso Tigo',
                      text: resp.mensaje,
                      confirmButtonColor: '#3085d6',
                      confirmButtonText: 'Aceptar',
                    }).then((result) =>{
                      if(result.isConfirmed){
                        this.router.navigateByUrl('/auth/login');
                      }
                    });
                    
                  }else{
                    Swal.fire({
                      icon:'error',
                      title: 'Aso Tigo',
                      text: resp.mensaje
                    });
                  }
                }, (err) => {
                  console.log(err);
                }
              )
            });
          }
        })
      }
    }, err => {
      console.log(err);
      Swal.fire({
        icon:'error',
        title: 'Aso Tigo',
        text: JSON.stringify(err)
      })
    });
  }
}
