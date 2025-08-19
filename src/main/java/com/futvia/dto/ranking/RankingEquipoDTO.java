package com.futvia.dto.ranking;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingEquipoDTO extends BaseDTO {
    private AmbitoTipo alcanceTipo; private Long alcanceId;
    private String metrica; private Double valor; private String periodo;
    private Long equipoId; private String equipoNombre;
}