package com.futvia.model.club;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.RolTecnico;
import com.futvia.model.rbac.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "cuerpos_tecnicos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CuerpoTecnico extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "equipo_id", foreignKey = @ForeignKey(name = "fk_ct_equipo"))
    private Equipo equipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RolTecnico rolTecnico;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // opcional: si el DT tiene cuenta

    @Column(length = 160)
    private String nombreVisible; // si no hay usuario
}