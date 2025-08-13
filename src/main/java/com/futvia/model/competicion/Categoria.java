package com.futvia.model.competicion;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias", uniqueConstraints = {
        @UniqueConstraint(name = "uk_categoria_comp_nombre", columnNames = {"competicion_id", "nombre"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Categoria extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "competicion_id", foreignKey = @ForeignKey(name = "fk_cat_comp"))
    private Competicion competicion;

    @Column(nullable = false, length = 60)
    private String nombre; // Mayor A, Juvenil, etc.
}