package com.empresa.gestion_empresa.repository;

import com.empresa.gestion_empresa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByStockLessThan(Integer stock); // Para encontrar productos con stock bajo

    // Método para buscar productos por nombre con paginación
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}

