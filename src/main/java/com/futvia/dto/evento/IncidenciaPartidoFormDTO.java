package com.futvia.dto.evento;

import com.futvia.model.common.enums.TipoIncidencia;
import lombok.*;

// Forms
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class IncidenciaPartidoFormDTO {
    private Long partidoId; private Integer minuto; private String tiempo; private TipoIncidencia tipo;
    private Long equipoId; private Long jugadorPrincipalId; private Long jugadorInvolucradoId; private String detalle;
}