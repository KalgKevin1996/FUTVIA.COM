package com.futvia.dto.audit;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExportLogDTO extends BaseDTO {
    private Long usuarioId; private String usuarioNombre;
    private String tipo;
    private String parametros; // JSON
    private Long archivoId;
}