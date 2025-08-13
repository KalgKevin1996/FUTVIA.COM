package com.futvia.model.contenido;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.partido.Partido;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "actas_partido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActaPartido extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "partido_id", foreignKey = @ForeignKey(name = "fk_acta_partido"))
    private Partido partido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "archivo_id", foreignKey = @ForeignKey(name = "fk_acta_archivo"))
    private Archivo archivo;

    @Column(length = 255)
    private String firmantes; // texto o JSON según convenga
}