package com.futvia.model.partido;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.EstadoPartido;
import com.futvia.model.competicion.Categoria;
import com.futvia.model.competicion.Temporada;
import com.futvia.model.club.Equipo;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "partidos", indexes = {@Index(name = "ix_partido_fecha", columnList = "fechaHora")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Partido extends BaseEntity {
    @ManyToOne(optional = false) @JoinColumn(name = "temporada_id")
    private Temporada temporada;

    @ManyToOne @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne @JoinColumn(name = "jornada_id")
    private Jornada jornada;

    @ManyToOne @JoinColumn(name = "estadio_id")
    private Estadio estadio;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(optional = false) @JoinColumn(name = "equipo_local_id")
    private Equipo equipoLocal;

    @ManyToOne(optional = false) @JoinColumn(name = "equipo_visitante_id")
    private Equipo equipoVisitante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPartido estado = EstadoPartido.PROGRAMADO;

    @Column(length = 40)
    private String tipoPartido; // opcional si difiere del de la competición

    // marcador final (opcional, se puede derivar de incidencias)
    private Integer golesLocal;
    private Integer golesVisitante;
}