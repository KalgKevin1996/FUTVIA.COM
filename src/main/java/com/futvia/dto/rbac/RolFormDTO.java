package com.futvia.dto.rbac;
import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import com.futvia.model.common.enums.RolNombre;
import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class RolFormDTO {
    private RolNombre nombre;
    private Integer nivel;
}