package com.bankvision.backend.controller;
import com.bankvision.backend.entity.Producto;
import com.bankvision.backend.request.Mensaje;
import com.bankvision.backend.request.ProductoRequest;
import com.bankvision.backend.services.ProductoService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
    
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list(){
        List<Producto> list = productoService.list();
        
        return new ResponseEntity<List<Producto>>(list, HttpStatus.OK);
    }
    
    //Buscar por ID
    @GetMapping("detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        //validar existencia
        if (!productoService.existById(id)){
            return new ResponseEntity(new Mensaje("El producto no existe"), HttpStatus.NOT_FOUND);
        }
        
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
    
    //Buscar por nombre
    @GetMapping("detailnombre/{nombre}")
    public ResponseEntity<Producto> getById(@PathVariable("nombre") String nombre){
        //validar si existe
        if (!productoService.existByNombre(nombre)){
            return new ResponseEntity(new Mensaje("El producto no existe"), HttpStatus.NOT_FOUND);
        }
        
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
    
    
    //Crear producto
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoRequest productoRequest){
        //validaciones
        if(StringUtils.isBlank(productoRequest.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }else if(productoRequest.getPrecio() == null || productoRequest.getPrecio() < 0){
            return new ResponseEntity(new Mensaje("El precio tiene que ser mayor a 0"), HttpStatus.BAD_REQUEST);
        }else if(productoService.existByNombre(productoRequest.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        //creacion producto
        Producto producto = new Producto(productoRequest.getNombre(), productoRequest.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("Producto creado."), HttpStatus.OK);
    }    
    
    
    //actualizar productos
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoRequest productoRequest){
        //validaciones
        if (!productoService.existById(id)){
           return new ResponseEntity(new Mensaje("El producto no existe"), HttpStatus.NOT_FOUND);
        }
        if(StringUtils.isBlank(productoRequest.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }else if(productoRequest.getPrecio() == null ||  productoRequest.getPrecio() < 0){
            return new ResponseEntity(new Mensaje("El precio tiene que ser mayor a 0"), HttpStatus.BAD_REQUEST);
        }else if(productoService.existByNombre(productoRequest.getNombre()) && productoService.getByNombre(productoRequest.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoRequest.getNombre());
        producto.setPrecio(productoRequest.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("Producto actualizado."), HttpStatus.OK);
    }  
    
    //eliminar producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id")int id){
        if (!productoService.existById(id)){
           return new ResponseEntity(new Mensaje("El producto no existe."), HttpStatus.NOT_FOUND);
        }
        
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("Producto eliminado."), HttpStatus.OK);
    }
}

