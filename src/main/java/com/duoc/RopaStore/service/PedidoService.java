package com.duoc.RopaStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.RopaStore.model.Cliente;
import com.duoc.RopaStore.model.Pedido;
import com.duoc.RopaStore.repository.ClienteRepository;
import com.duoc.RopaStore.repository.PedidoRepository;

@Service 
public class PedidoService {
    
    @Autowired 
    private final PedidoRepository pedidoRepo;

    @Autowired 
    private final ClienteRepository clienteRepo;

    // Constructor para inyectar las dependencias
    public PedidoService(PedidoRepository pedidoRepo, ClienteRepository  clienteRepo) {
        this.pedidoRepo = pedidoRepo;
        this.clienteRepo = clienteRepo;
    }

    // listar todos los pedidos
    public List<Pedido> obtenerPedidos() {
        return pedidoRepo.findAll();
    }   

    // listar pedidos por cliente
    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        return pedidoRepo.findByClienteId(clienteId);
    }

    // crear un nuevo pedido
    public Pedido crearPedido(Pedido pedido, Long clienteId) {
       Cliente cliente = clienteRepo.findById(clienteId).orElseThrow(() -> new RuntimeException("ERROR: El cliente con ID " + clienteId + " no existe."));

       pedido.setCliente(cliente);
       return pedidoRepo.save(pedido);
    }

    // actualizar un pedido existente
    public Optional<Pedido> actualizarPedido(Long id, Pedido pedidoActualizado) {
        return pedidoRepo.findById(id).map(pedido -> {
            // no se permite actualizar el cliente ni la fecha de pedido pq están vinculados a la creación del pedido (compra).
            pedido.setTotal(pedidoActualizado.getTotal());
            pedido.setEstado(pedidoActualizado.getEstado());
            return pedidoRepo.save(pedido);
        });
    }

    // eliminar pedido
    public boolean eliminarPedido(Long id) {
        if (pedidoRepo.existsById(id)) {
            pedidoRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
