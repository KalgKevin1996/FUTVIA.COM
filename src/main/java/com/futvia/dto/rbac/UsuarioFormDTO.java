package com.futvia.dto.rbac;
import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class UsuarioFormDTO {
    private String nombre;
    private String apellido;
    private String email;
    private boolean estado;
}
