package com.futvia.dto.partido;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.EstadoPartido;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JornadaDTO extends BaseDTO {
    private Long temporadaId; private String temporadaNombre;
    private Long categoriaId; private String categoriaNombre; // opcional
    private Integer numero;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
}