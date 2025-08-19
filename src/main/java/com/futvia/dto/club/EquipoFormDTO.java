package com.futvia.dto.club;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class EquipoFormDTO {
    private Long clubId; private Long competicionId; private Long categoriaId;
    private String nombreVisible; private String escudoUrl;
}