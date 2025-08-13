package com.futvia.model.rbac;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permisos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_permisos_codigo", columnNames = {"codigo"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Permiso extends BaseEntity {
    @Column(nullable = false, length = 80)
    private String codigo; // p.ej. PARTIDO_EDITAR

    @Column(length = 255)
    private String descripcion;
}