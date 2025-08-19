package com.futvia.dto.partido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TernaDetalleFormDTO {
    private Long ternaId;
    private Long arbitroId;
    private com.futvia.model.common.enums.RolArbitral rol;
}