package com.duoc.RopaStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.RopaStore.model.Sucursal;
import com.duoc.RopaStore.repository.SucursalRepository;

@Service 
public class SucursalService {
    
    @Autowired 
    private final SucursalRepository sucursalRepo;

    // Constructor para inyectar las dependencias
    public SucursalService(SucursalRepository sucursalRepo) {
        this.sucursalRepo = sucursalRepo;
    }

    // solo nos interesa crear sucursales.
    // crear una nueva sucursal
    public Sucursal crearSucursal(Sucursal sucursal) {
        return sucursalRepo.save(sucursal);
    }
    
}
