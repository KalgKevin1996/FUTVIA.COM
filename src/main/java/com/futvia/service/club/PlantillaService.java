package com.futvia.service.club;

import com.futvia.dto.club.*;
import org.springframework.data.domain.*;

public interface PlantillaService {
    Page<PlantillaDTO> porEquipo(Long equipoId, Long temporadaId, Pageable p);
    PlantillaDTO agregar(PlantillaFormDTO f);
    PlantillaDTO editar(Long id, PlantillaFormDTO f);
    void activar(Long id, boolean activo);
    void eliminar(Long id);
}