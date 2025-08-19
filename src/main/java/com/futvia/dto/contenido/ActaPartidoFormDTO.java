package com.futvia.dto.contenido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ActaPartidoFormDTO {
    private Long partidoId;
    private Long archivoId;
    private String firmantes;
}