import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {tap, Observable, of} from 'rxjs';

import { ICredencial } from '../interfaces/credencial.interfaces';
import { IDatoAdicionalUsuario, ILogin, Usuario } from '../interfaces/login.interfaces';

const BASE_URL = environment.baseUrl;
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuario:ILogin= {
    parametrosGlobales:{},
    recordarSesion:false,
    usuario:{
      Correo:'',
      Celular:'',
      nroSocio:'',
      valido:false,
      documento:0,
      mensaje:'',
      nombre:'',
      solicitarNuevoPass:'',
      codaso:'',
      status:''
    }
  };

  constructor(private http:HttpClient) { }

  get getUser():Usuario | null {

    if(!this.usuario?.usuario){
      return null;
    }
    return this.usuario.usuario;
  }

  /* login(credencial:ICredencial):Observable<ILogin>{

      return this.http.post<ILogin>(`${BASE_URL}/loginPost`, credencial).pipe(
      tap((resp:ILogin) => this.usuario = resp),
      tap((resp:ILogin) => localStorage.setItem('usuario', JSON.stringify(resp.usuario)))
    );
  } */

    login(credencial:ICredencial):Observable<ILogin>{

      return this.http.post<ILogin>(`${BASE_URL}/loginWPost`, credencial).pipe(
      tap((resp:ILogin) => this.usuario = resp),
      tap((resp:ILogin) => localStorage.setItem('usuario', JSON.stringify(resp.usuario)))
    );
  }

  chequearAutenticacionUsuario():Observable<boolean> {
    const existeUsuario = localStorage.getItem('usuario') || null;
    if(!existeUsuario){
      return of(false);
    }else{
      const usuario:Usuario = JSON.parse(localStorage.getItem('usuario') || '') || {};
      this.usuario!.usuario = usuario;
      return of(true);
    }
  
  }

  actualizarDatoAdicionalUsuario(params:{
    nroSocio:string,
    Correo:string,
    Celular:string
  }):Observable<IDatoAdicionalUsuario>{
    return this.http.post<IDatoAdicionalUsuario>(`${BASE_URL}/actualizaDatos`, params)
    .pipe(
      tap(resp => console.log(resp)),
      tap(resp => of(resp))
    )
  }
  logout():void{
    localStorage.clear();
  }
}
