package com.futvia.service.geo;

import com.futvia.dto.geo.*;
import org.springframework.data.domain.*;

public interface ZonaService {
    Page<ZonaDTO> listar(Pageable p);
    Page<ZonaDTO> porMunicipio(Long municipioId, Pageable p);
    ZonaDTO crear(ZonaFormDTO f);
    ZonaDTO editar(Long id, ZonaFormDTO f);
    void eliminar(Long id);
}