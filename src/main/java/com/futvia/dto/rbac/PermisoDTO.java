package com.futvia.dto.rbac;
import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PermisoDTO {
    private String codigo;
    private String descripcion;
}