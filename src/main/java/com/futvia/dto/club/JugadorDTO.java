package com.futvia.dto.club;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JugadorDTO extends BaseDTO {
    private String nombre;
    private String apellido;
    private java.time.LocalDate fechaNacimiento;
    private com.futvia.model.club.enums.Posicion posicion;
    private Integer dorsalPreferido;
    private String fotoUrl;
    private Boolean perfilPublico;
    private Long usuarioId; // opcional
}