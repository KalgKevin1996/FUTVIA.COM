package com.futvia.model.rbac;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="usuarios", indexes =  {
        @Index(name = "ix_usuarios_email", columnList = "email", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario extends BaseEntity {
    @Column(nullable = false, length = 80)
    private String nombre;

    @Column(nullable = false, length = 80)
    private String apellido;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean estado = true; // activo/inactivo
}
