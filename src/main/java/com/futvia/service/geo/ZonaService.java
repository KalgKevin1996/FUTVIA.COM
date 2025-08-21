// src/main/java/com/futvia/service/geo/ZonaService.java
package com.futvia.service.geo;

import com.futvia.dto.geo.ZonaDTO;
import com.futvia.dto.geo.ZonaFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ZonaService {
    Page<ZonaDTO> listar(Long municipioId, Integer numero, Pageable pageable);
    ZonaDTO obtener(Long id);
    ZonaDTO crear(ZonaFormDTO form);
    ZonaDTO editar(Long id, ZonaFormDTO form);
    void eliminar(Long id);
}
