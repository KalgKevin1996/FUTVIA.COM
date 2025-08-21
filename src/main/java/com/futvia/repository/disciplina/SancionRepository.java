// src/main/java/com/futvia/repository/disciplina/SancionRepository.java
package com.futvia.repository.disciplina;

import com.futvia.model.disciplina.Sancion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SancionRepository extends JpaRepository<Sancion, Long> {

    // Por jugador / equipo (listado y paginado)
    List<Sancion> findByJugador_Id(Long jugadorId);
    Page<Sancion> findByJugador_Id(Long jugadorId, Pageable pageable);

    List<Sancion> findByEquipo_Id(Long equipoId);
    Page<Sancion> findByEquipo_Id(Long equipoId, Pageable pageable);

    // Sanciones activas a la fecha (inicio <= fecha <= fin OR fin null)
    @Query("""
           select s from Sancion s
            where s.inicio <= :fecha
              and (s.fin is null or s.fin >= :fecha)
           """)
    Page<Sancion> findActivas(@Param("fecha") LocalDate fecha, Pageable pageable);

    // Activas para un jugador / equipo a la fecha
    @Query("""
           select s from Sancion s
            where s.jugador.id = :jugadorId
              and s.inicio <= :fecha
              and (s.fin is null or s.fin >= :fecha)
           """)
    List<Sancion> findActivasPorJugador(@Param("jugadorId") Long jugadorId, @Param("fecha") LocalDate fecha);

    @Query("""
           select s from Sancion s
            where s.equipo.id = :equipoId
              and s.inicio <= :fecha
              and (s.fin is null or s.fin >= :fecha)
           """)
    List<Sancion> findActivasPorEquipo(@Param("equipoId") Long equipoId, @Param("fecha") LocalDate fecha);

    // Rango de fechas (cualquier intersección con [desde, hasta])
    @Query("""
           select s from Sancion s
            where (s.inicio <= :hasta)
              and (s.fin is null or s.fin >= :desde)
           """)
    Page<Sancion> findByInterseccionRango(@Param("desde") LocalDate desde,
                                          @Param("hasta") LocalDate hasta,
                                          Pageable pageable);

    // Por origen de reporte disciplinario
    Page<Sancion> findByOrigenReporte_Id(Long reporteId, Pageable pageable);
}
