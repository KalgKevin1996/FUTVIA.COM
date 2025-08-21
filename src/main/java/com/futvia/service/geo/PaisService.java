// src/main/java/com/futvia/service/geo/PaisService.java
package com.futvia.service.geo;

import com.futvia.dto.geo.PaisDTO;
import com.futvia.dto.geo.PaisFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaisService {
    Page<PaisDTO> listar(String q, Pageable pageable);
    PaisDTO obtener(Long id);
    PaisDTO crear(PaisFormDTO form);
    PaisDTO editar(Long id, PaisFormDTO form);
    void eliminar(Long id);
}
