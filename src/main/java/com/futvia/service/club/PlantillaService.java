// src/main/java/com/futvia/service/club/PlantillaService.java
package com.futvia.service.club;

import com.futvia.dto.club.PlantillaDTO;
import com.futvia.dto.club.PlantillaFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlantillaService {
    Page<PlantillaDTO> listar(Long equipoId, Long temporadaId, Pageable pageable);
    PlantillaDTO obtener(Long id);
    PlantillaDTO agregar(PlantillaFormDTO form);
    PlantillaDTO editar(Long id, PlantillaFormDTO form);
    void eliminar(Long id);
}
