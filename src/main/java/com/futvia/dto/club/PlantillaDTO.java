package com.futvia.dto.club;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlantillaDTO extends BaseDTO {
    private Long equipoId; private String equipoNombre;
    private Long jugadorId; private String jugadorNombreCompleto;
    private Long temporadaId; private String temporadaNombre;
    private Integer dorsal;
    private boolean activo;
}
