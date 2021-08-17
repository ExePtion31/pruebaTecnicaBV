package com.bankvision.backend.services;
import com.bankvision.backend.entity.Producto;
import com.bankvision.backend.repository.ProductoRepository;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;
    
    public List<Producto> list(){
        return productoRepository.findAll();
    }
    
    public Optional<Producto> getOne(int id){
        return productoRepository.findById(id);
    }
    
    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }
    
    public void save(Producto producto){
        productoRepository.save(producto);
    }
    
    public void delete(int id){
        productoRepository.deleteById(id);
    }
    
    public boolean existById(int id){
        return productoRepository.existsById(id);
    }
    
    public boolean existByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }
}
