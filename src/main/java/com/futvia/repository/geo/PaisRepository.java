// src/main/java/com/futvia/repository/geo/PaisRepository.java
package com.futvia.repository.geo;

import com.futvia.model.geo.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    // Básicos
    Optional<Pais> findByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);

    // Búsqueda
    Page<Pais> findByNombreContainingIgnoreCase(String q, Pageable pageable);
    List<Pais> findTop10ByNombreStartingWithIgnoreCase(String prefijo);
}
