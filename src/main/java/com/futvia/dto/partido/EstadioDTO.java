package com.futvia.dto.partido;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadioDTO extends BaseDTO {
    private String nombre;
    private String direccion;
    private Long municipioId; private String municipioNombre;
}