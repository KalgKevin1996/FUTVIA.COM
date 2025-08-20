package com.futvia.dto.contenido;

import com.futvia.model.common.enums.ArchivoTipo;
import lombok.*;

// Forms
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class ArchivoFormDTO {
    private ArchivoTipo tipo;
    private String s3Key;
    private String urlPublica;
    private String mime;
    private Long tamano;
    private String creadoPor; }