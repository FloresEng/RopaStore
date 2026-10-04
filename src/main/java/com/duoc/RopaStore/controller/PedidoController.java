package com.duoc.RopaStore.controller;

import com.duoc.RopaStore.model.Cliente;
import com.duoc.RopaStore.model.Pedido;
import com.duoc.RopaStore.service.ClienteService;
import com.duoc.RopaStore.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final ClienteService pedidoCliente;

    public PedidoController(PedidoService pedidoService, ClienteService pedidoCliente) {
        this.pedidoService = pedidoService;
        this.pedidoCliente = pedidoCliente;
    }

    //LISTAR TODOS LOS PEDIDOS
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return ResponseEntity.ok(pedidoService.obtenerPedidos());
    }

    //BUSCAR PEDIDO POR CLIENTE ID
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Pedido>> buscarPedidoPorClienteRut(@PathVariable("id") Long clienteId){
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    //CREAR UN PEDIDO NUEVO
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido, Long idCliente){
        Pedido pedidoGuardado = pedidoService.crearPedido(pedido, idCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado);
    }

    //ACTUALIZAR PEDIDO DE ACUERDO A SU ID
    @PutMapping("/cliente/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido){
        return pedidoService.actualizarPedido(id, pedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ELIMINAR PEDIDO DE ACUERDO A SU ID
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id){
        boolean eliminado = pedidoService.eliminarPedido(id);

        if(!eliminado){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


    /*

    //LISTAR TODOS LOS CLIENTES
    public ResponseEntity<List<Pedido>> buscarPedidosPorCliente(@PathVariable("id") Long clienteId){
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);

    //BUSCARCLIENTE POR EL ID
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> buscarClienteRut(@PathVariable String rut){
        return clienteService.obtenerClientePorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //CREAR UN CLIENTE NUEVO
    @PostMapping("/cliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        Cliente clienteGuardado = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    //ACTUALIZAR UN CLIENTE DE ACUERDO A SU ID
    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.actualizarCliente(id, cliente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ELIMINAR CLIENTE DE ACUERDO A SU ID
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        boolean eliminado = clienteService.eliminarCliente(id);

        if(!eliminado){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    */

}
