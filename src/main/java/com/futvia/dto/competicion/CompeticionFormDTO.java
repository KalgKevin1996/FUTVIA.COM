package com.futvia.dto.competicion;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.TipoCompeticion;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public
class CompeticionFormDTO {
    private String nombre;
    private TipoCompeticion tipo;
    private Long reglamentoArchivoId;
    private Long organizadorId;
    private Long paisId; private Long departamentoId; private Long municipioId; private Long zonaId;
}