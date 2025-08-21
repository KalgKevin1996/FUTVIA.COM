// src/main/java/com/futvia/repository/geo/MunicipioRepository.java
package com.futvia.repository.geo;

import com.futvia.model.geo.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    // Unicidad por departamento (nombre municipio único dentro del departamento)
    Optional<Municipio> findByDepartamento_IdAndNombreIgnoreCase(Long departamentoId, String nombre);
    boolean existsByDepartamento_IdAndNombreIgnoreCase(Long departamentoId, String nombre);

    // Listados y búsqueda
    Page<Municipio> findByDepartamento_Id(Long departamentoId, Pageable pageable);
    List<Municipio> findByDepartamento_Id(Long departamentoId);
    Page<Municipio> findByNombreContainingIgnoreCase(String q, Pageable pageable);
    Page<Municipio> findByDepartamento_IdAndNombreContainingIgnoreCase(Long departamentoId, String q, Pageable pageable);

    // Mantenimiento
    long deleteByDepartamento_Id(Long departamentoId);
    long countByDepartamento_Id(Long departamentoId);
}
