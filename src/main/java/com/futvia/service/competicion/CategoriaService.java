package com.futvia.service.competicion;

import com.futvia.dto.competicion.*;
import org.springframework.data.domain.*;

public interface CategoriaService {
    Page<CategoriaDTO> listar(Pageable p);
    Page<CategoriaDTO> porCompeticion(Long competicionId, Pageable p);
    CategoriaDTO crear(CategoriaFormDTO f);
    CategoriaDTO editar(Long id, CategoriaFormDTO f);
    void eliminar(Long id);
}
