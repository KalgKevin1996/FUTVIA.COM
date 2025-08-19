package com.futvia.dto.club;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class PlantillaFormDTO {
    private Long equipoId;
    private Long jugadorId;
    private Long temporadaId;
    private Integer dorsal;
    private Boolean activo;
}