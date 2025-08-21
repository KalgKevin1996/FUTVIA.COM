// src/main/java/com/futvia/service/disciplina/SancionService.java
package com.futvia.service.disciplina;

import com.futvia.dto.disciplina.SancionDTO;
import com.futvia.dto.disciplina.SancionFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface SancionService {
    SancionDTO obtener(Long id);

    // Listados básicos
    Page<SancionDTO> listarPorJugador(Long jugadorId, Pageable pageable);
    Page<SancionDTO> listarPorEquipo(Long equipoId, Pageable pageable);
    Page<SancionDTO> listarActivas(LocalDate fecha, Pageable pageable);
    Page<SancionDTO> listarPorRango(LocalDate desde, LocalDate hasta, Pageable pageable);
    Page<SancionDTO> listarPorReporte(Long reporteId, Pageable pageable);

    // Creación / edición / cierre
    SancionDTO crear(SancionFormDTO form);
    SancionDTO actualizar(Long id, SancionFormDTO form);
    SancionDTO cerrar(Long id, LocalDate fin); // fija fecha fin (inclusive)
    void eliminar(Long id);

    // Atajos
    List<SancionDTO> activasDeJugador(Long jugadorId, LocalDate fecha);
    List<SancionDTO> activasDeEquipo(Long equipoId, LocalDate fecha);
}
