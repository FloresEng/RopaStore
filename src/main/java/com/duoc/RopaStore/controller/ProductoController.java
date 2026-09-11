package com.duoc.RopaStore.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.service.ProductoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/productos")
public class ProductoController{

    // llamamos a productoservice
    private final ProductoService productoService;

    // inyección por constructor
    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> obtenerCatalogo(){
        return productoService.ordenarCatalogoPrecio();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Producto> obtenerCategoria(@PathVariable String categoria){
        return productoService.ordenarCategoriaPrecio(categoria);
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto){
        return productoService.guardarProducto(producto);
    }
    
    
}