package com.duoc.RopaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.RopaStore.service.ProductoService;



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

    
    
}