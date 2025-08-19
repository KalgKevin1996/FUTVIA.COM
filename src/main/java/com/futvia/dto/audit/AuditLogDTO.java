package com.futvia.dto.audit;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuditLogDTO extends BaseDTO {
    private Long usuarioId; private String usuarioNombre;
    private String accion; private String entidad; private Long entidadId;
    private String detalle; private String ip;
}