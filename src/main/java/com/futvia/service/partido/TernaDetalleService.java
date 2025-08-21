// src/main/java/com/futvia/service/partido/TernaDetalleService.java
package com.futvia.service.partido;

import com.futvia.dto.partido.TernaDetalleDTO;
import com.futvia.dto.partido.TernaDetalleFormDTO;

import java.util.List;

public interface TernaDetalleService {

    TernaDetalleDTO obtener(Long id);

    List<TernaDetalleDTO> listarPorTerna(Long ternaId);

    TernaDetalleDTO crear(TernaDetalleFormDTO form);

    TernaDetalleDTO actualizar(Long id, TernaDetalleFormDTO form);

    void eliminar(Long id);
}
