package com.futvia.model.contenido;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.ArchivoTipo;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "archivos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Archivo extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ArchivoTipo tipo;

    @Column(nullable = false, length = 200)
    private String s3Key; // ruta/clave en S3

    @Column(length = 255)
    private String urlPublica; // si aplicara

    @Column(length = 80)
    private String mime;

    private Long tamano;

    @Column(length = 120)
    private String creadoPor; // opcional: email o id de usuario
}