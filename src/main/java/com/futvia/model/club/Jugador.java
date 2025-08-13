package com.futvia.model.club;

import com.futvia.model.common.BaseEntity;
import com.futvia.model.club.enums.Posicion;
import com.futvia.model.rbac.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity @Table(name = "jugadores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Jugador extends BaseEntity {
    @Column(nullable = false, length = 80)
    private String nombre;

    @Column(nullable = false, length = 80)
    private String apellido;

    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Posicion posicion;

    private Integer dorsalPreferido; // dorsal general, el de temporada va en Plantilla

    @Column(length = 255)
    private String fotoUrl; // o Archivo

    private Boolean perfilPublico = true;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // opcional
}