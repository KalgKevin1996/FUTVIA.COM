// src/main/java/com/futvia/service/competicion/CategoriaService.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.CategoriaDTO;
import com.futvia.dto.competicion.CategoriaFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {
    Page<CategoriaDTO> listar(Long competicionId, String q, Pageable pageable);
    CategoriaDTO obtener(Long id);
    CategoriaDTO crear(CategoriaFormDTO form);
    CategoriaDTO editar(Long id, CategoriaFormDTO form);
    void eliminar(Long id);
}
