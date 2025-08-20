package com.futvia.dto.evento;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class EstadisticaEquipoPartidoFormDTO {
    private Long partidoId; private Long equipoId;
    private Integer posesion; private Integer tiros; private Integer tirosArco;
    private Integer corners; private Integer faltas; private Integer offsides;
    private Integer amarillas; private Integer rojas; private Integer pases; private Integer precisionPases; private Double xg;
}