package com.futvia.dto.rbac;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.RolNombre;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioDTO extends BaseDTO {
    private String nombre;
    private String apellido;
    private String email;
    private boolean estado;
}
