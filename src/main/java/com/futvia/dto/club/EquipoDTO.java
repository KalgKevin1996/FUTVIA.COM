package com.futvia.dto.club;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoDTO extends BaseDTO {
    private Long clubId; private String clubNombre;
    private Long competicionId; private String competicionNombre; // opcional
    private Long categoriaId; private String categoriaNombre;     // opcional
    private String nombreVisible;
    private String escudoUrl;
}