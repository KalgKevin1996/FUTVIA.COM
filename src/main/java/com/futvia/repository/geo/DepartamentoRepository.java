// src/main/java/com/futvia/repository/geo/DepartamentoRepository.java
package com.futvia.repository.geo;

import com.futvia.model.geo.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    // Unicidad por país (nombre depto único dentro del país)
    Optional<Departamento> findByPais_IdAndNombreIgnoreCase(Long paisId, String nombre);
    boolean existsByPais_IdAndNombreIgnoreCase(Long paisId, String nombre);

    // Listados y búsqueda
    Page<Departamento> findByPais_Id(Long paisId, Pageable pageable);
    List<Departamento> findByPais_Id(Long paisId);
    Page<Departamento> findByNombreContainingIgnoreCase(String q, Pageable pageable);
    Page<Departamento> findByPais_IdAndNombreContainingIgnoreCase(Long paisId, String q, Pageable pageable);

    // Mantenimiento
    long deleteByPais_Id(Long paisId);
    long countByPais_Id(Long paisId);
}
