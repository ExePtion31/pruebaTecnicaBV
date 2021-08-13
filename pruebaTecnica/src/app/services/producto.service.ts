import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  productoURL = 'http://localhost:8080/producto/';

  constructor(private httpClient:HttpClient) { }

  //Obtener lista
  public lista(): Observable<Producto[]>{
    return this.httpClient.get<Producto[]>(this.productoURL + 'lista');
  }

  //Obtener detalle
  public detail(id: number): Observable<Producto>{
    return this.httpClient.get<Producto>(this.productoURL + `detail/${id}`);
  }

  //Obtener detalle por nombre
  public detailName(nombre: string): Observable<Producto>{
    return this.httpClient.get<Producto>(this.productoURL + `detailname/${nombre}`);
  }

  //Crear producto
  public save(producto: Producto): Observable<any>{
    return this.httpClient.post<any>(this.productoURL + 'create', producto);
  }

  //Actualizar producto
  public update(id: number, producto: Producto): Observable<any>{
    return this.httpClient.put<any>(this.productoURL + `update/${id}`, producto);
  }

  //Borrar producto
  public delete(id:number): Observable<any>{
    return this.httpClient.delete<any>(this.productoURL + `delete/${id}`);
  }
}
