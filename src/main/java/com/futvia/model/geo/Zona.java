package com.futvia.model.geo;

import com.futvia.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zonas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_zona_mun_numero", columnNames = {"municipio_id", "numero"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Zona extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "municipio_id", foreignKey = @ForeignKey(name = "fk_zona_mun"))
    private Municipio municipio;

    @Column(nullable = false)
    private Integer numero; // Guatemala usa zonas numeradas
}