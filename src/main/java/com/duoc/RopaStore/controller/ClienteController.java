package com.duoc.RopaStore.controller;

import com.duoc.RopaStore.model.Cliente;
import com.duoc.RopaStore.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //LISTAR TODOS LOS CLIENTES
    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente(){
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorRut(@PathVariable String rut){
        return clienteService.obtenerClientePorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        Cliente clienteGuardado = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.actualizarCliente(id, cliente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        boolean eliminado = clienteService.eliminarCliente(id);

        if(!eliminado){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
