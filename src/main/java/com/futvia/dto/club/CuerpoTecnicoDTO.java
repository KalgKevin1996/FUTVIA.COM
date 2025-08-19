package com.futvia.dto.club;


import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuerpoTecnicoDTO extends BaseDTO {
    private Long equipoId; private String equipoNombre;
    private com.futvia.model.common.enums.RolTecnico rolTecnico;
    private Long usuarioId; // opcional
    private String nombreVisible; // si no hay usuario
}
