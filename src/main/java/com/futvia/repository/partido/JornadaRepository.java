// src/main/java/com/futvia/repository/partido/JornadaRepository.java
package com.futvia.repository.partido;

import com.futvia.model.partido.Jornada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {

    // Ya existente: listar por temporada
    List<Jornada> findByTemporada_Id(Long temporadaId);

    // Buscar por (temporada, número)
    Optional<Jornada> findByTemporada_IdAndNumero(Long temporadaId, Integer numero);

    // Rango de fechas para calendarios
    List<Jornada> findByTemporada_IdAndFechaInicioBetween(Long temporadaId, LocalDate desde, LocalDate hasta);

    // Paginado + orden
    Page<Jornada> findByTemporada_IdOrderByNumeroAsc(Long temporadaId, Pageable pageable);
}
