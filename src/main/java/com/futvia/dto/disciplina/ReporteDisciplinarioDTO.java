package com.futvia.dto.disciplina;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReporteDisciplinarioDTO extends BaseDTO {
    private Long partidoId; private String partidoResumen;
    private Long reportanteId; private String reportanteNombre;
    private String descripcion;
}