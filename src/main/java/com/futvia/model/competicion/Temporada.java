package com.futvia.model.competicion;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity @Table(name = "temporadas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Temporada extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "competicion_id", foreignKey = @ForeignKey(name = "fk_temp_comp"))
    private Competicion competicion;

    @Column(nullable = false, length = 60)
    private String nombre; // "Apertura 2025" o año

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}