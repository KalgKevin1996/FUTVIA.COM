package com.futvia.dto.disciplina;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SancionDTO extends BaseDTO {
    private Long jugadorId; private String jugadorNombre;
    private Long equipoId; private String equipoNombre;
    private String tipo;
    private java.time.LocalDate inicio;
    private java.time.LocalDate fin;
    private String detalle;
    private Long origenReporteId;
}
