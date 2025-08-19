package com.futvia.dto.competicion;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.TipoCompeticion;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrganizadorDTO extends BaseDTO {
    private String nombre;
    private String tipo;
}