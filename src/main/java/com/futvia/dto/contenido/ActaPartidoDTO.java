package com.futvia.dto.contenido;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActaPartidoDTO extends BaseDTO {
    private Long partidoId; private String partidoResumen; // opcional para vistas
    private Long archivoId; private String firmantes;
}