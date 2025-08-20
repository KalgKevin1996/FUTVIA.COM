package com.futvia.dto.partido;

import lombok.*;

// Forms
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class JornadaFormDTO {
    private Long temporadaId; private Long categoriaId;
    private Integer numero; private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
}