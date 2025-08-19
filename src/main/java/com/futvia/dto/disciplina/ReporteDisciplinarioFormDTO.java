package com.futvia.dto.disciplina;

import lombok.*;

// Forms
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ReporteDisciplinarioFormDTO {
    private Long partidoId;
    private Long reportanteId;
    private String descripcion;
}