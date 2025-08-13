package com.futvia.model.club;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.rbac.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "arbitros")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Arbitro extends BaseEntity {
    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario; // opcional

    @Column(length = 60)
    private String nivel;

    @Column(length = 120)
    private String asociacion;
}