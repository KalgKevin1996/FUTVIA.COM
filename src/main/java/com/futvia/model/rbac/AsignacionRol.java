package com.futvia.model.rbac;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.AmbitoTipo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asignaciones_rol", uniqueConstraints = {
        @UniqueConstraint(name = "uk_asignacion_unica", columnNames = {
                "usuario_id", "rol_id", "ambito_tipo", "ambito_id"
        })
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AsignacionRol extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_asigrol_usuario"))
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "fk_asigrol_rol"))
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "ambito_tipo", length = 20)
    private AmbitoTipo ambitoTipo; // null/ GLOBAL para global

    @Column(name = "ambito_id")
    private Long ambitoId; // id de país/depto/competición/temporada/equipo, según ambitoTipo
}