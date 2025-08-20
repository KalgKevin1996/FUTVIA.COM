package com.futvia.dto.geo;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MunicipioFormDTO { private Long departamentoId; private String nombre; }