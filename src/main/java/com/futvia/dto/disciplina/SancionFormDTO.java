package com.futvia.dto.disciplina;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class SancionFormDTO {
    private Long jugadorId;
    private Long equipoId;
    private String tipo;
    private java.time.LocalDate inicio;
    private java.time.LocalDate fin;
    private String detalle;
    private Long origenReporteId;
}