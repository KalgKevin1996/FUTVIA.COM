package com.futvia.model.partido;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity @Table(name = "ternas_arbitrales")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TernaArbitral extends BaseEntity {
    @OneToOne(optional = false)
    @JoinColumn(name = "partido_id", unique = true, foreignKey = @ForeignKey(name = "fk_terna_partido"))
    private Partido partido;

    @OneToMany(mappedBy = "terna", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TernaDetalle> detalles = new ArrayList<>();
}