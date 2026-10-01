package com.duoc.RopaStore.repository;
import java.util.List;
import java.util.Optional;

import com.duoc.RopaStore.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    //lista para obtener productos por categoría
    List<Producto> findByCategoriaIgnoreCase(String categoria);

    //obtener producto por id
    Optional<Producto> findById(Long id);

    //lista para obtener productos por sucursal
    List<Producto> findBySucursalId(String sucursalId);
}