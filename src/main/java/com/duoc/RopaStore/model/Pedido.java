package com.duoc.RopaStore.model;

import java.time.LocalDate; 
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "pedidos")
public class Pedido {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne     
    @JoinColumn (name = "cliente_id", nullable = false)
    private Cliente cliente;

    @CreationTimestamp
    @Column (nullable = false)
    private LocalDate fechaPedido;

    @Column (nullable = false)
    private int total;

    @Enumerated(EnumType.STRING) 
    @Column (nullable = false, length = 30)
    private EstadoPedido estado;

    // constructor vacío
    public Pedido(){
    }

    // constructor sin id pq se genera solo y es autoincremental. 
    // Se asigna la fecha de pedido automáticamente con @CreationTimestamp
    public Pedido(Cliente cliente, int total, EstadoPedido estado){
        this.cliente = cliente;
        this.total = total;
        this.estado = estado;
    }

    // getters
    public Long getId(){
        return id;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public LocalDate getFechaPedido(){
        return fechaPedido;
    }

    public int getTotal(){
        return total;
    }

    public EstadoPedido getEstado(){
        return estado;
    }

    // setters
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setFechaPedido(LocalDate fechaPedido){
        this.fechaPedido = fechaPedido;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public void setEstado(EstadoPedido estado){
        this.estado = estado;
    }


}
