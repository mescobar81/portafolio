import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ICatastroTarjeta, ITarjetaEliminada, ITarjetaRecuperada } from '../interfaces/catastro.interface';

const BASE_URL = environment.baseUrl;
@Injectable({
  providedIn: 'root'
})
export class CatastroService {

  constructor(private http: HttpClient) { }


  catastrarTarjeta(nroSocio:Number):Observable<ICatastroTarjeta>{
    let params = {
      nroSocio:nroSocio
    }
    console.log(params);
    
    return this.http.post<ICatastroTarjeta>(`${BASE_URL}/catastroWTarjeta`, params)
    .pipe(
      tap((resp:ICatastroTarjeta) => of(resp))
    )
  }

  recuperarTarjeta(nroSocio:Number):Observable<ITarjetaRecuperada>{
    return this.http.post<ITarjetaRecuperada>(`${BASE_URL}/recuperarTarjetas`, {nroSocio})
    .pipe(
      tap(resp => of(resp))
      
    )
  }

  eliminarTarjeta(token:string, nroSocio:number):Observable<ITarjetaEliminada>{
    const params = {
      nroSocio: nroSocio,
      alias_token: token
    }
    return this.http.post<ITarjetaEliminada>(`${BASE_URL}/eliminarTarjeta`, params)
    .pipe(
      tap(resp => of(resp))
    )
  }
}
