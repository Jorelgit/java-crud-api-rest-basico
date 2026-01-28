package com.jorel.apirest.repasopoobasico9practicaapirest.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity     // Anotacion para indicar que es una entidad
public class Producto {

    @Id     // Decorador para indicar que es el Id o Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Esto indica que el Id se vaya creabdo de forma autoincremental y automaticamente
    private Long id;

    private String nombre;
    private double precio;


    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
