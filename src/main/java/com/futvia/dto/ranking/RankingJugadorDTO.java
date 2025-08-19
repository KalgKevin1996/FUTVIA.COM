package com.futvia.dto.ranking;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingJugadorDTO extends BaseDTO {
    private AmbitoTipo alcanceTipo; private Long alcanceId;
    private String metrica; private Double valor; private String periodo;
    private Long jugadorId; private String jugadorNombre;
}