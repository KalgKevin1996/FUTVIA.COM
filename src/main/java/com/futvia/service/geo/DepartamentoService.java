// src/main/java/com/futvia/service/geo/DepartamentoService.java
package com.futvia.service.geo;

import com.futvia.dto.geo.DepartamentoDTO;
import com.futvia.dto.geo.DepartamentoFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartamentoService {
    Page<DepartamentoDTO> listar(Long paisId, String q, Pageable pageable);
    DepartamentoDTO obtener(Long id);
    DepartamentoDTO crear(DepartamentoFormDTO form);
    DepartamentoDTO editar(Long id, DepartamentoFormDTO form);
    void eliminar(Long id);
}
