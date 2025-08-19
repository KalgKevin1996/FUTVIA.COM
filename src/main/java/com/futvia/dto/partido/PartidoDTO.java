package com.futvia.dto.partido;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.EstadoPartido;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoDTO extends BaseDTO {
    private Long temporadaId; private String temporadaNombre;
    private Long categoriaId; private String categoriaNombre;
    private Long jornadaId; private Integer jornadaNumero;
    private Long estadioId; private String estadioNombre;
    private java.time.LocalDateTime fechaHora;
    private Long equipoLocalId; private String equipoLocalNombre;
    private Long equipoVisitanteId; private String equipoVisitanteNombre;
    private EstadoPartido estado;
    private String tipoPartido;
    private Integer golesLocal; private Integer golesVisitante;
}