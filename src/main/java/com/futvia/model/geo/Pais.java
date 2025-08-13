package com.futvia.model.geo;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity @Table(name = "paises")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Pais extends BaseEntity {
    @Column(nullable = false, length = 80, unique = true)
    private String nombre;
}
