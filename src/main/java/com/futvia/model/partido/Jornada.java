package com.futvia.model.partido;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.competicion.Categoria;
import com.futvia.model.competicion.Temporada;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity @Table(name = "jornadas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Jornada extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "temporada_id", foreignKey = @ForeignKey(name = "fk_jornada_temporada"))
    private Temporada temporada;

    @ManyToOne @JoinColumn(name = "categoria_id")
    private Categoria categoria; // opcional

    @Column(nullable = false)
    private Integer numero;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}