// src/main/java/com/futvia/repository/competicion/TemporadaRepository.java
package com.futvia.repository.competicion;

import com.futvia.model.competicion.Temporada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TemporadaRepository extends JpaRepository<Temporada, Long> {
    // existente
    List<Temporada> findByCompeticion_Id(Long competicionId);

    // variantes
    Page<Temporada> findByCompeticion_Id(Long competicionId, Pageable pageable);
    Page<Temporada> findByCompeticion_IdAndNombreContainingIgnoreCase(Long competicionId, String q, Pageable pageable);

    // rangos de fechas (útiles para calendario)
    List<Temporada> findByFechaInicioBetween(LocalDate desde, LocalDate hasta);
    List<Temporada> findByFechaFinBetween(LocalDate desde, LocalDate hasta);

    // ayuda para evitar duplicados de nombre dentro de una misma competición
    boolean existsByCompeticion_IdAndNombreIgnoreCase(Long competicionId, String nombre);
}
