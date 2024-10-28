package com.empresa.gestion_empresa.service;

import com.empresa.gestion_empresa.model.Producto;
import com.empresa.gestion_empresa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Guardar un producto nuevo o actualizar un producto existente
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar un producto por su ID
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Obtener un producto por su ID
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Obtener productos con stock bajo
    public List<Producto> obtenerProductosConStockBajo(Integer stock) {
        return productoRepository.findByStockLessThan(stock);
    }
}
