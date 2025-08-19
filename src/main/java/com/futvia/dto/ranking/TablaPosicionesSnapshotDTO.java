package com.futvia.dto.ranking;

import com.futvia.dto.common.BaseDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TablaPosicionesSnapshotDTO extends BaseDTO {
    private Long temporadaId; private String temporadaNombre;
    private Long categoriaId; private String categoriaNombre;
    private Long equipoId; private String equipoNombre;
    private Integer pj; private Integer pg; private Integer pe; private Integer pp;
    private Integer gf; private Integer gc; private Integer dg; private Integer pts;
    private java.time.LocalDate fechaCorte;
}