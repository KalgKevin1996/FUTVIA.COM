package com.futvia.service.partido;

import com.futvia.dto.partido.*;


import java.time.LocalDateTime;
import java.util.List;
public interface TernaArbitralService {
    TernaArbitralDTO crearParaPartido(Long partidoId);
    TernaArbitralDTO obtenerPorPartido(Long partidoId);
    TernaDetalleDTO agregarArbitro(Long ternaId, Long arbitroId, com.futvia.model.common.enums.RolArbitral rol);
    void quitarDetalle(Long ternaDetalleId);
}