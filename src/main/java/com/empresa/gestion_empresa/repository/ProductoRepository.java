package com.empresa.gestion_empresa.repository;

import com.empresa.gestion_empresa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByStockLessThan(Integer stock); // Para encontrar productos con stock bajo
}
