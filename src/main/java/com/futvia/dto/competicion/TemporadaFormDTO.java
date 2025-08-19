package com.futvia.dto.competicion;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class TemporadaFormDTO {
    private Long competicionId;
    private String nombre;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
}