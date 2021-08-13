package com.bankvision.backend.request;
import javax.validation.constraints.*;

public class ProductoRequest {
    @NotBlank
    private String nombre;
    @Min(0)
    private Float precio;
    
    //Contructors
    public ProductoRequest() {
    }

    public ProductoRequest(String nombre, Float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    
}
