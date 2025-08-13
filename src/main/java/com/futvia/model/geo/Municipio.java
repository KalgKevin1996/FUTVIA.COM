package com.futvia.model.geo;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "municipios", uniqueConstraints = {
        @UniqueConstraint(name = "uk_mun_dep_nombre", columnNames = {"departamento_id", "nombre"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Municipio extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "departamento_id", foreignKey = @ForeignKey(name = "fk_mun_dep"))
    private Departamento departamento;

    @Column(nullable = false, length = 80)
    private String nombre;
}