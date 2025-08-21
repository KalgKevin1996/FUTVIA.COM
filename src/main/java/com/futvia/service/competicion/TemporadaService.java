// src/main/java/com/futvia/service/competicion/TemporadaService.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.TemporadaDTO;
import com.futvia.dto.competicion.TemporadaFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemporadaService {
    Page<TemporadaDTO> listar(Long competicionId, String q, Pageable pageable);
    TemporadaDTO obtener(Long id);
    TemporadaDTO crear(TemporadaFormDTO form);
    TemporadaDTO editar(Long id, TemporadaFormDTO form);
    void eliminar(Long id);
}
