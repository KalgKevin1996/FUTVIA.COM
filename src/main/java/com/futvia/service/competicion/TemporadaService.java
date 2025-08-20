package com.futvia.service.competicion;

import com.futvia.dto.competicion.*;
import org.springframework.data.domain.*;

public interface TemporadaService {
    Page<TemporadaDTO> listar(Pageable p);
    Page<TemporadaDTO> porCompeticion(Long competicionId, Pageable p);
    TemporadaDTO crear(TemporadaFormDTO f);
    TemporadaDTO editar(Long id, TemporadaFormDTO f);
    void eliminar(Long id);
}
