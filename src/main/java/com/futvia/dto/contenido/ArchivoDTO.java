package com.futvia.dto.contenido;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.ArchivoTipo;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArchivoDTO extends BaseDTO {
    private ArchivoTipo tipo;
    private String s3Key;
    private String urlPublica;
    private String mime;
    private Long tamano;
    private String creadoPor;
}
