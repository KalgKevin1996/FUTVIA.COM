package com.futvia.model.disciplina;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.club.Equipo;
import com.futvia.model.club.Jugador;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity @Table(name = "sanciones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Sancion extends BaseEntity {
    @ManyToOne @JoinColumn(name = "jugador_id")
    private Jugador jugador; // uno u otro

    @ManyToOne @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @Column(length = 80)
    private String tipo; // p.ej. suspensión, multa

    private LocalDate inicio;
    private LocalDate fin;

    @Column(length = 255)
    private String detalle;

    @ManyToOne @JoinColumn(name = "origen_reporte_id")
    private ReporteDisciplinario origenReporte;
}