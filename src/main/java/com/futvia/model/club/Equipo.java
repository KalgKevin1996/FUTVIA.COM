package com.futvia.model.club;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.competicion.Categoria;
import com.futvia.model.competicion.Competicion;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "equipos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipo extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "club_id", foreignKey = @ForeignKey(name = "fk_equipo_club"))
    private Club club;

    @ManyToOne @JoinColumn(name = "competicion_id")
    private Competicion competicion; // opcional

    @ManyToOne @JoinColumn(name = "categoria_id")
    private Categoria categoria; // opcional

    @Column(nullable = false, length = 120)
    private String nombreVisible;

    @Column(length = 255)
    private String escudoUrl; // o Archivo
}