package com.duoc.RopaStore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 12)
    private String rut;

    @Column (nullable = false, length = 100)
    private String nombre;

    @Column (nullable = false, length = 100)
    private String correo;

    @Column (nullable = false)
    private int telefono;

    @Column (nullable = false, length = 200)
    private String direccion;

    // constructor vacío
    public Cliente(){
    }

    // constructor sin id pq se genera solo y es autoincremental
    public Cliente(String rut, String nombre, String correo, int telefono, String direccion){
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // getters
    public Long getId(){
        return id;
    }

    public String getRut(){
        return rut;
    }  

    public String getNombre(){
        return nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public int getTelefono(){
        return telefono;
    }

    public String getDireccion(){
        return direccion;
    }

    // setters
    public void setRut(String rut){
        this.rut = rut;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public void setTelefono(int telefono){
        this.telefono = telefono;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }


}
