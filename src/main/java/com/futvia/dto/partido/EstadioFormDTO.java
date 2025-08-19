package com.futvia.dto.partido;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadioFormDTO {
    private String nombre;
    private String direccion;
    private Long municipioId;
}
