// src/main/java/com/futvia/service/club/EquipoService.java
package com.futvia.service.club;

import com.futvia.dto.club.EquipoDTO;
import com.futvia.dto.club.EquipoFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipoService {
    Page<EquipoDTO> listar(Long competicionId, Long categoriaId, Pageable pageable);
    EquipoDTO obtener(Long id);
    EquipoDTO crear(EquipoFormDTO form);
    EquipoDTO editar(Long id, EquipoFormDTO form);
    void eliminar(Long id);
}
