// src/main/java/com/futvia/service/geo/MunicipioService.java
package com.futvia.service.geo;

import com.futvia.dto.geo.MunicipioDTO;
import com.futvia.dto.geo.MunicipioFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MunicipioService {
    Page<MunicipioDTO> listar(Long departamentoId, String q, Pageable pageable);
    MunicipioDTO obtener(Long id);
    MunicipioDTO crear(MunicipioFormDTO form);
    MunicipioDTO editar(Long id, MunicipioFormDTO form);
    void eliminar(Long id);
}
