package com.futvia.service.club;

import com.futvia.dto.club.*;
import org.springframework.data.domain.*;

public interface JugadorService {
    Page<JugadorDTO> listar(String q, Pageable p);
    Page<JugadorDTO> porEquipoTemporada(Long equipoId, Long temporadaId, Pageable p);
    JugadorDTO crear(JugadorFormDTO f);
    JugadorDTO editar(Long id, JugadorFormDTO f);
    void eliminar(Long id);
}