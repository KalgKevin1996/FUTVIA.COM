package com.futvia.dto.evento;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.TipoIncidencia;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IncidenciaPartidoDTO extends BaseDTO {
    private Long partidoId;
    private Integer minuto;
    private String tiempo; // PT/ST/ET (opcional)
    private TipoIncidencia tipo;
    private Long equipoId; private String equipoNombre;
    private Long jugadorPrincipalId; private String jugadorPrincipalNombre;
    private Long jugadorInvolucradoId; private String jugadorInvolucradoNombre;
    private String detalle;
}