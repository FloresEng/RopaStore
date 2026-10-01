package com.duoc.RopaStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.RopaStore.model.Cliente;
import com.duoc.RopaStore.repository.ClienteRepository;

@Service 
public class ClienteService {

    @Autowired 
    private final ClienteRepository clienteRepo;

    // Constructor para inyectar las dependencias
    public ClienteService(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    // obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        return clienteRepo.findAll();
    }

    // obtener cliente por rut
    public Optional<Cliente> obtenerClientePorRut(String rut) {
        return clienteRepo.findByRutIgnoreCase(rut);
    }

    // crear nuevo cliente
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    // actualizar un cliente existente
    // buscamos por id, en caso de que haya existido error de tipeo en rut y se requiera modificar.
    public Optional<Cliente> actualizarCliente(Long id, Cliente clienteActualizado) {
        return clienteRepo.findById(id).map(cliente -> {
            cliente.setRut(clienteActualizado.getRut());
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setCorreo(clienteActualizado.getCorreo());
            cliente.setTelefono(clienteActualizado.getTelefono());
            cliente.setDireccion(clienteActualizado.getDireccion());
            return clienteRepo.save(cliente);
        });
    }

    // eliminar un cliente por id
    public boolean eliminarCliente(Long id) {
        if (clienteRepo.existsById(id)) {
            clienteRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
