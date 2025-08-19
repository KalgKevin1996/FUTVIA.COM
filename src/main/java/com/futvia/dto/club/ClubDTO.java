package com.futvia.dto.club;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClubDTO extends BaseDTO {
    private String nombre;
    private String escudoUrl;
    private Long municipioId;   // sede opcional
    private String municipioNombre;
}