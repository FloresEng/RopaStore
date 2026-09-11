package com.duoc.RopaStore.service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.RopaStore.exception.ProductoValidoException;
import com.duoc.RopaStore.model.Producto;
import com.duoc.RopaStore.repository.ProductoRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductoService{

    private final ProductoRepository productoRepo;

    // inyección por constructor
    @Autowired
    public ProductoService(ProductoRepository productoRepo){
        this.productoRepo = productoRepo;
    }

    @PostConstruct
    public void CargarCatalogo(){
        System.out.println("Cargando productos...");
        //cada vez que se inicie el proyecto va a cargar los productos a la BD
        if (productoRepo.count() == 0){
            productoRepo.save(new Producto("Polera negra", 14990, "Poleras"));
            productoRepo.save(new Producto("Polera blanca", 14990, "Poleras"));
            productoRepo.save(new Producto("Polera niño", 9990, "Poleras"));
            productoRepo.save(new Producto("Buzo niño", 15990, "Pantalones"));
            productoRepo.save(new Producto("Pantalon cafe", 32990, "Pantalones"));
            productoRepo.save(new Producto("Pantalon negro", 32990, "Pantalones"));
            productoRepo.save(new Producto("Chaqueta cuero cafe", 63900, "Chaquetas"));
            productoRepo.save(new Producto("Chaqueta cuero negra", 63900, "Chaquetas"));
            productoRepo.save(new Producto("Poleron niño", 22990, "Chaquetas"));
        }
    }

    // catálogo de productos ordenados por precio
    public List<Producto> ordenarCatalogoPrecio(){

        //traemos los productos con find all
        List<Producto> productosBD = productoRepo.findAll();

        //los pasamos a la lista ordenada
        ArrayList<Producto> productosOrdenadosPrecio = new ArrayList<>(productosBD);

        //comparamos por precio y los ordenamos
        productosOrdenadosPrecio.sort(Comparator.comparingDouble(Producto::getPrecio));

        return productosOrdenadosPrecio;
    }

        // catálogo de productos ordenados por categoria
    public List<Producto> ordenarCategoriaPrecio(String categoria){

        // manejo de excepciones
        if (categoria == null || categoria.isEmpty()) {
            throw new ProductoValidoException("Debe indicar una categoría válida (poleras, pantalones, chaquetas).");
        }

        //traemos los productos con find all
        List<Producto> productosFiltrados = productoRepo.findByCategoriaIgnoreCase(categoria);

        //los pasamos a la lista ordenada
        ArrayList<Producto> productosCategoriaPrecio = new ArrayList<>(productosFiltrados);

        //comparamos por precio y los ordenamos
        productosCategoriaPrecio.sort(Comparator.comparingDouble(Producto::getPrecio));

        return productosCategoriaPrecio;
    }

    // método para agregar un producto a la base de datos
    public Producto guardarProducto(Producto producto){

        // manejo de excepciones
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new ProductoValidoException("El nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() <= 0) {
            throw new ProductoValidoException("El precio del producto debe ser mayor a cero.");
        }
        if (producto.getCategoria() == null || producto.getCategoria().isEmpty()) {
            throw new ProductoValidoException("La categoría del producto es obligatoria.");
        }
        return productoRepo.save(producto);
    }


}