package com.futvia.model.rbac;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol_permiso", uniqueConstraints = {
        @UniqueConstraint(name = "uk_rol_permiso", columnNames = {"rol_id", "permiso_id"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RolPermiso extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "fk_rolpermiso_rol"))
    private Rol rol;

    @ManyToOne(optional = false)
    @JoinColumn(name = "permiso_id", foreignKey = @ForeignKey(name = "fk_rolpermiso_permiso"))
    private Permiso permiso;
}