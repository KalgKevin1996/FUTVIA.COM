package com.futvia.model.partido;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.geo.Municipio;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "estadios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Estadio extends BaseEntity {
    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @ManyToOne @JoinColumn(name = "municipio_id")
    private Municipio municipio;
}