package com.futvia.model.partido;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.common.enums.RolArbitral;
import com.futvia.model.club.Arbitro;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "terna_detalle", uniqueConstraints = {
        @UniqueConstraint(name = "uk_terna_arbitro_rol", columnNames = {"terna_id", "arbitro_id", "rol"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TernaDetalle extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "terna_id", foreignKey = @ForeignKey(name = "fk_detalle_terna"))
    private TernaArbitral terna;

    @ManyToOne(optional = false)
    @JoinColumn(name = "arbitro_id", foreignKey = @ForeignKey(name = "fk_detalle_arbitro"))
    private Arbitro arbitro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RolArbitral rol;
}