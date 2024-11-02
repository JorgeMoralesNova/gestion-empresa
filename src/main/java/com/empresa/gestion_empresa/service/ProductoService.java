package com.empresa.gestion_empresa.service;

import com.empresa.gestion_empresa.model.Producto;
import com.empresa.gestion_empresa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener productos paginados
    public Page<Producto> obtenerProductosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productoRepository.findAll(pageable);
    }
    // Obtener productos con paginación y búsqueda
    public Page<Producto> obtenerProductosPaginados(String terminoBusqueda, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (terminoBusqueda == null || terminoBusqueda.isEmpty()) {
            return productoRepository.findAll(pageable);
        } else {
            return productoRepository.findByNombreContainingIgnoreCase(terminoBusqueda, pageable);
        }
    }

    public List<String> obtenerSugerenciasPorNombre(String term) {
        return productoRepository.findByNombreContainingIgnoreCase(term, PageRequest.of(0, 5))
                .stream()
                .map(Producto::getNombre)
                .collect(Collectors.toList());
    }

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
