// src/main/java/com/futvia/service/club/ArbitroService.java
package com.futvia.model.club.club;

import com.futvia.dto.club.ArbitroDTO;
import com.futvia.dto.club.ArbitroFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArbitroService {
    Page<ArbitroDTO> listar(Pageable pageable);
    ArbitroDTO obtener(Long id);
    ArbitroDTO crear(ArbitroFormDTO form);
    ArbitroDTO editar(Long id, ArbitroFormDTO form);
    void eliminar(Long id);
}
