package com.futvia.service.club;

import com.futvia.dto.club.*;
import org.springframework.data.domain.*;

public interface EquipoService {
    Page<EquipoDTO> listar(Pageable p);
    Page<EquipoDTO> porClub(Long clubId, Pageable p);
    Page<EquipoDTO> porCompeticion(Long competicionId, Pageable p);
    Page<EquipoDTO> porCategoria(Long categoriaId, Pageable p);
    EquipoDTO crear(EquipoFormDTO f);
    EquipoDTO editar(Long id, EquipoFormDTO f);
    void eliminar(Long id);
}