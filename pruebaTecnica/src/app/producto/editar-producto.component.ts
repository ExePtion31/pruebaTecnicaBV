import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Producto } from '../models/producto';
import { ProductoService } from '../services/producto.service';

@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css']
})
export class EditarProductoComponent implements OnInit {

  producto:Producto;
  constructor(
    private productoService: ProductoService,
    private activatedRoute: ActivatedRoute,
    private toaster: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getData();
  }

  //Cargar info del producto
  getData(): void{
    const id = this.activatedRoute.snapshot.params.id;
    this.productoService.detail(id).subscribe(
      data=>{
        this.producto = data;
      },
      err =>{
        this.toaster.error(err.error.mensaje, 'Error',{
          timeOut:3000
        });
        this.router.navigate(['/']);
      }
    )
  }

  //Funcion de actualizar
  onUpdate():void{
    const id = this.activatedRoute.snapshot.params.id;
    this.productoService.update(id, this.producto).subscribe(
      data => {
        this.toaster.success(data.mensaje, 'Exito',{
          timeOut:3000
        });
        this.router.navigate(['/'])
      },
      err => {
        this.toaster.error(err.error.mensaje, 'Error',{
          timeOut:3000
        });
        this.router.navigate(['/']);
      }
    )
  }
}
