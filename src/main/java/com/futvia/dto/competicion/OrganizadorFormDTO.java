package com.futvia.dto.competicion;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.TipoCompeticion;
import lombok.*;

// Forms
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrganizadorFormDTO { private String nombre; private String tipo; }