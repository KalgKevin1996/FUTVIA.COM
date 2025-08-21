// src/main/java/com/futvia/service/competicion/CompeticionService.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.CompeticionDTO;
import com.futvia.dto.competicion.CompeticionFormDTO;
import com.futvia.model.common.enums.TipoCompeticion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompeticionService {
    Page<CompeticionDTO> listar(Long organizadorId, TipoCompeticion tipo, String q, Pageable pageable);
    CompeticionDTO obtener(Long id);
    CompeticionDTO crear(CompeticionFormDTO form);
    CompeticionDTO editar(Long id, CompeticionFormDTO form);
    void eliminar(Long id);
}
