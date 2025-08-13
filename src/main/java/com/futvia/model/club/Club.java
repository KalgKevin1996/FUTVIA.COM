package com.futvia.model.club;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.geo.Municipio;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "clubes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Club extends BaseEntity {
    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 255)
    private String escudoUrl; // o relacionar con Archivo

    @ManyToOne @JoinColumn(name = "municipio_id")
    private Municipio municipio; // sede opcional
}