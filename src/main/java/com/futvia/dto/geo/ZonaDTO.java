package com.futvia.dto.geo;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ZonaDTO extends BaseDTO {
    private Integer numero;
    private Long municipioId;
    private String municipioNombre;
}