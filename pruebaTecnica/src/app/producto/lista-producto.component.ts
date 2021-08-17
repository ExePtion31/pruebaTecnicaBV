import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Producto } from '../models/producto';
import { ProductoService } from '../services/producto.service';

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit {

  productos:Producto[] = [];

  constructor(
    private productoService: ProductoService,
    private toastr: ToastrService,
    ) { }

  ngOnInit(): void {
    this.cargarProducto();
  }

  //Cargar lista de productos
  cargarProducto(): void{
    this.productoService.lista().subscribe(
      data => {
        this.productos = data;
      },
      err => {
        this.toastr.warning("Error al cargar los productos", 'Error',{
          timeOut:3000
        });
      }
    );
  }


  //Borrar producto
  borrar(id: number){
    this.productoService.delete(id).subscribe(
      data => {
        this.toastr.success(data.mensaje, 'Exito',{
          timeOut:3000
        });
        this.cargarProducto();
      },
      err =>{
        this.toastr.error(err.error.mensaje, 'Error',{
          timeOut:3000
        });
      }
    )
  }
}
