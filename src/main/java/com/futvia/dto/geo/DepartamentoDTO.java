package com.futvia.dto.geo;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DepartamentoDTO extends BaseDTO {
    private String nombre;
    private Long paisId;
    private String paisNombre;
}