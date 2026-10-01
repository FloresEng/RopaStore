package com.duoc.RopaStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.RopaStore.model.EstadoPedido;
import com.duoc.RopaStore.model.Pedido;

@Repository 
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //lista para obtener pedidos por cliente
    List<Pedido> findByClienteId(Long clienteId);

    //lista para obtener pedidos por estado
    List<Pedido> findByEstado(EstadoPedido estado);
    
}
