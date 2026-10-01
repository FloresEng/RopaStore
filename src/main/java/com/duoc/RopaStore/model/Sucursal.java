package com.duoc.RopaStore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 100)
    private String nombre;

    // constructor vacío
    public Sucursal(){
    }

    // constructor sin id pq se genera solo y es autoincremental
    public Sucursal(String nombre){
        this.nombre = nombre;
    }

    // getters
    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    // setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
}
