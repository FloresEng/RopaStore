package com.duoc.RopaStore.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "productos")
public class Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column (nullable = false)
    private double precio;

    
    @Column(nullable = false, length = 100) 
    private String categoria;

    @Column(nullable = false)
    private int stock;

    @ManyToOne 
    @JoinColumn (name = "sucursal_id", nullable = false)
    private Sucursal sucursal;


    // constructor vacío
    public Producto(){
    }

    // constructor sin id pq se genera solo y es autoincremental
    public Producto(String nombre, double precio, String categoria, int stock){
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
    }

    // getters
    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public String getCategoria(){
        return categoria;
    }
    public int getStock(){
        return stock;
    }

    public Sucursal getSucursal(){
        return sucursal;
    }

    // setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public void setSucursal(Sucursal sucursal){
        this.sucursal = sucursal;
    }

}