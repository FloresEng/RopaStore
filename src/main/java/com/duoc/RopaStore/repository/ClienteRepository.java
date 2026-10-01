package com.duoc.RopaStore.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.RopaStore.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // listar cliente por rut
    Optional<Cliente> findByRutIgnoreCase(String rut);

}
