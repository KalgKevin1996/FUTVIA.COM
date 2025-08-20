package com.futvia.dto.club;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class JugadorFormDTO {
    private String nombre; private String apellido; private java.time.LocalDate fechaNacimiento;
    private com.futvia.model.club.enums.Posicion posicion; private Integer dorsalPreferido;
    private String fotoUrl; private Boolean perfilPublico; private Long usuarioId;
}