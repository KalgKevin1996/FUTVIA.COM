package com.futvia.dto.rbac;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.RolNombre;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RolPermisoDTO extends BaseDTO {
    private Long rolId;
    private Long permisoId;
}