package com.futvia.model.disciplina;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.partido.Partido;
import com.futvia.model.rbac.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "reportes_disciplinarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReporteDisciplinario extends BaseEntity {
    @ManyToOne(optional = false) @JoinColumn(name = "partido_id")
    private Partido partido;

    @ManyToOne @JoinColumn(name = "reportante_id")
    private Usuario reportante; // árbitro/comisario (usuario)

    @Lob
    private String descripcion;
}