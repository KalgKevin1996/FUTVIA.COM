package com.futvia.model.audit;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.contenido.Archivo;
import com.futvia.model.rbac.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "export_log")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ExportLog extends BaseEntity {
    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false, length = 20)
    private String tipo; // PDF, EXCEL

    @Lob
    private String parametros; // JSON con filtros del reporte

    @ManyToOne @JoinColumn(name = "archivo_id")
    private Archivo archivo; // resultado del export
}