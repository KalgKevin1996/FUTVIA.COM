package com.futvia.dto.partido;

import com.futvia.model.common.enums.EstadoPartido;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoFormDTO {
    private Long temporadaId; private Long categoriaId; private Long jornadaId; private Long estadioId;
    private java.time.LocalDateTime fechaHora;
    private Long equipoLocalId; private Long equipoVisitanteId;
    private EstadoPartido estado; private String tipoPartido;
}