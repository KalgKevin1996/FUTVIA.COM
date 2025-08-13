package com.futvia.model.club;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.competicion.Temporada;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plantillas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_plantilla_equipo_jugador_temp", columnNames = {"equipo_id", "jugador_id", "temporada_id"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Plantilla extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "equipo_id", foreignKey = @ForeignKey(name = "fk_plantilla_equipo"))
    private Equipo equipo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "jugador_id", foreignKey = @ForeignKey(name = "fk_plantilla_jugador"))
    private Jugador jugador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "temporada_id", foreignKey = @ForeignKey(name = "fk_plantilla_temporada"))
    private Temporada temporada;

    private Integer dorsal;

    @Column(nullable = false)
    private boolean activo = true;
}