package com.futvia.dto.club;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class CuerpoTecnicoFormDTO {
    private Long equipoId;
    private com.futvia.model.common.enums.RolTecnico rolTecnico;
    private Long usuarioId; private String nombreVisible; }