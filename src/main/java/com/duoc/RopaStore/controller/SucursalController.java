package com.duoc.RopaStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.model.Sucursal;
import com.duoc.RopaStore.service.ProductoService;
import com.duoc.RopaStore.service.SucursalService;

@RestController
@RequestMapping ("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;
    private final ProductoService productoService;

    @Autowired 
    public SucursalController(SucursalService sucursalService, ProductoService productoService) {
        this.sucursalService = sucursalService;
        this.productoService = productoService;
    }

    @PostMapping 
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody Sucursal sucursal) {
        Sucursal createdSucursal = sucursalService.crearSucursal(sucursal);
        return ResponseEntity.status(201).body(createdSucursal);
    }

    @GetMapping("/{sucursalId}/inventario")
    public ResponseEntity<List<Producto>> consultarInventario(@PathVariable Long sucursalId) {
        return ResponseEntity.ok(productoService.obtenerProductosPorSucursal(sucursalId));
    }
    
    
}
