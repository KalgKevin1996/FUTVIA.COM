package com.futvia.model.geo;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departamentos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_dep_pais_nombre", columnNames = {"pais_id", "nombre"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Departamento extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "pais_id", foreignKey = @ForeignKey(name = "fk_dep_pais"))
    private Pais pais;

    @Column(nullable = false, length = 80)
    private String nombre;
}