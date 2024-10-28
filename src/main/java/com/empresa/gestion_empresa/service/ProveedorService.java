package com.empresa.gestion_empresa.service;

import com.empresa.gestion_empresa.model.Proveedor;
import com.empresa.gestion_empresa.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener todos los proveedores
    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    // Guardar un proveedor nuevo o actualizar un proveedor existente
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Eliminar un proveedor por su ID
    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }

    // Obtener un proveedor por su ID
    public Proveedor obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }
}
