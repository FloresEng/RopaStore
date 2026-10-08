package com.duoc.RopaStore.controller;

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
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido, @RequestParam Long idCliente){
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

}
