package com.futvia.model.competicion;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "organizadores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Organizador extends BaseEntity {
    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 40)
    private String tipo; // federacion, asociacion, privado, etc.
}