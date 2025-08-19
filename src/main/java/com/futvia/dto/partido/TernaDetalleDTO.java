package com.futvia.dto.partido;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TernaDetalleDTO extends BaseDTO {
    private Long ternaId;
    private Long arbitroId; private String arbitroNombreVisible;
    private com.futvia.model.common.enums.RolArbitral rol;
}