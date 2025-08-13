package com.futvia.model.audit;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.rbac.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "audit_log", indexes = {
        @Index(name = "ix_audit_entidad", columnList = "entidad, entidadId")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuditLog extends BaseEntity {
    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false, length = 80)
    private String accion; // CREATE, UPDATE, DELETE, EXPORT, LOGIN, etc.

    @Column(nullable = false, length = 80)
    private String entidad; // nombre lógico de la entidad afectada

    private Long entidadId;

    @Column(length = 255)
    private String detalle;

    @Column(length = 60)
    private String ip;
}