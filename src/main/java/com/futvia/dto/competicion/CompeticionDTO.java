package com.futvia.dto.competicion;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.TipoCompeticion;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CompeticionDTO extends BaseDTO {
    private String nombre;
    private TipoCompeticion tipo;
    private Long reglamentoArchivoId; // Archivo (PDF)
    private Long organizadorId;
    private String organizadorNombre;
    // alcance geográfico opcional
    private Long paisId; private String paisNombre;
    private Long departamentoId; private String departamentoNombre;
    private Long municipioId; private String municipioNombre;
    private Long zonaId; private Integer zonaNumero;
}