package com.duoc.RopaStore.controller;

import com.duoc.RopaStore.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.RopaStore.service.ProductoService;

import java.util.List;


@RestController
@RequestMapping("/api/productos")
public class ProductoController{

    // llamamos a productoservice
    private final ProductoService productoService;


    // inyección por constructor
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /*
    * PRODUCTOS
    * */

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoId(@PathVariable Long id){
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, @RequestParam Long sucursalId){
        Producto productoGuardado = productoService.crearProducto(producto, sucursalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        return productoService.actualizarProducto(id, producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        boolean eliminado = productoService.eliminarProducto(id);

        if(!eliminado){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}