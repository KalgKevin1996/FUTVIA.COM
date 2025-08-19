
package com.futvia.dto.rbac;
import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class AsignacionRolDTO {
    private Long usuarioId;
    private Long rolId;
    private AmbitoTipo ambitoTipo;
    private Long ambitoId;
}
