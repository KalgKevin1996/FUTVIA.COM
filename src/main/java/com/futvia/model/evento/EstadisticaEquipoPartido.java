package com.futvia.model.evento;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.club.Equipo;
import com.futvia.model.partido.Partido;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estadisticas_equipo_partido", uniqueConstraints = {
        @UniqueConstraint(name = "uk_stats_partido_equipo", columnNames = {"partido_id", "equipo_id"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EstadisticaEquipoPartido extends BaseEntity {
    @ManyToOne(optional = false) @JoinColumn(name = "partido_id")
    private Partido partido;

    @ManyToOne(optional = false) @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    private Integer posesion; // %
    private Integer tiros;
    private Integer tirosArco;
    private Integer corners;
    private Integer faltas;
    private Integer offsides;
    private Integer amarillas;
    private Integer rojas;
    private Integer pases;
    private Integer precisionPases; // %
    private Double xg; // opcional
}