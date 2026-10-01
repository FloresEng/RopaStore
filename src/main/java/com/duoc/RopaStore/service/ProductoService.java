package com.duoc.RopaStore.service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.RopaStore.exception.ProductoValidoException;
import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.model.Sucursal;
import com.duoc.RopaStore.repository.ProductoRepository;
import com.duoc.RopaStore.repository.SucursalRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductoService{

    @Autowired 
    private final ProductoRepository productoRepo;

    @Autowired 
    private final SucursalRepository sucursalRepo;

    // Constructor para inyectar las dependencias
    public ProductoService(ProductoRepository productoRepo, SucursalRepository sucursalRepo) {
        this.productoRepo = productoRepo;
        this.sucursalRepo = sucursalRepo;
    }

    // obtener todos los productos en todas las sucursales
    public List<Producto> obtenerProductos() {
        return productoRepo.findAll();
    }

      // obtener producto por id
    public Producto obtenerProductoPorId(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    // obtener productos por categoría
    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return productoRepo.findByCategoriaIgnoreCase(categoria);
    }  

    // obtener productos por sucursal
    public List<Producto> obtenerProductosPorSucursal(String sucursalId) {
        return productoRepo.findBySucursalId(sucursalId);
    }

    // crear un nuevo producto
    public Producto crearProducto(Producto producto, Long sucursalId) {
        Sucursal sucursal = sucursalRepo.findById(sucursalId).orElseThrow(() -> new RuntimeException("ERROR: La sucursal con ID " + sucursalId + " no existe."));
        
        producto.setSucursal(sucursal);
        return productoRepo.save(producto); 
    }

    // actualizar un producto existente
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepo.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setStock(productoActualizado.getStock());
            return productoRepo.save(producto);
        }).orElseThrow(() -> new RuntimeException("ERROR: El producto con ID " + id + " no existe."));

    }

    // eliminar un producto
    public boolean eliminarProducto(Long id) {
        if (productoRepo.existsById(id)) {
            productoRepo.deleteById(id);
            return true;
        }
        return false;
    }
}