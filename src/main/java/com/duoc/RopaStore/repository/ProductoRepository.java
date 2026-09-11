package com.duoc.RopaStore.repository;
import java.util.List;
import com.duoc.RopaStore.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    //lista para obtener productos por categoría
    List<Producto> findByCategoriaIgnoreCase(String categoria);
}