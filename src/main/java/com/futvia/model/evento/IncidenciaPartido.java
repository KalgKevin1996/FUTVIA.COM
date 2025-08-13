package com.futvia.model.evento;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.TipoIncidencia;
import com.futvia.model.partido.Partido;
import com.futvia.model.club.Equipo;
import com.futvia.model.club.Jugador;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "incidencias_partido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IncidenciaPartido extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "partido_id", foreignKey = @ForeignKey(name = "fk_incidencia_partido"))
    private Partido partido;

    @Column(nullable = false)
    private Integer minuto; // puede complementarse con tiempo (PT/ ST/ET)

    @Column(length = 10)
    private String tiempo; // opcional: "PT", "ST", etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoIncidencia tipo;

    @ManyToOne @JoinColumn(name = "equipo_id")
    private Equipo equipo; // quien ejecuta/recibe

    @ManyToOne @JoinColumn(name = "jugador_principal_id")
    private Jugador jugadorPrincipal;

    @ManyToOne @JoinColumn(name = "jugador_involucrado_id")
    private Jugador jugadorInvolucrado; // opcional

    @Column(length = 255)
    private String detalle;
}