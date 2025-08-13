package com.futvia.model.rbac;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.RolNombre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "uk_roles_nombre", columnNames = {"nombre"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Rol extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private RolNombre nombre; // MASTER, ADMIN_LIGA, etc.

    @Column(nullable = false)
    private Integer nivel = 1; // libertad para jerarquizar
}