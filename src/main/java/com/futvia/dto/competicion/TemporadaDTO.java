package com.futvia.dto.competicion;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.TipoCompeticion;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TemporadaDTO extends BaseDTO {
    private Long competicionId;
    private String competicionNombre;
    private String nombre; // Apertura 2025
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
}