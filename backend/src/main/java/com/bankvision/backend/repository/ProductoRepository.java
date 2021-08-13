package com.bankvision.backend.repository;
import com.bankvision.backend.entity.Producto;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
