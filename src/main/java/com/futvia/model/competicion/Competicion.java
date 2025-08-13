package com.futvia.model.competicion;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.TipoCompeticion;
import com.futvia.model.geo.*;
import com.futvia.model.contenido.Archivo;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "competiciones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Competicion extends BaseEntity {
    @Column(nullable = false, length = 120)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoCompeticion tipo;

    @ManyToOne
    @JoinColumn(name = "reglamento_archivo_id", foreignKey = @ForeignKey(name = "fk_comp_reglamento_archivo"))
    private Archivo reglamentoPdf; // PDF en S3 (Archivo.tipo = REGLAMENTO_PDF)

    @ManyToOne
    @JoinColumn(name = "organizador_id", foreignKey = @ForeignKey(name = "fk_comp_org"))
    private Organizador organizador;

    // Alcance geográfico opcional
    @ManyToOne @JoinColumn(name = "pais_id") private Pais pais;
    @ManyToOne @JoinColumn(name = "departamento_id") private Departamento departamento;
    @ManyToOne @JoinColumn(name = "municipio_id") private Municipio municipio;
    @ManyToOne @JoinColumn(name = "zona_id") private Zona zona;
}