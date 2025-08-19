package com.futvia.dto.club;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArbitroDTO extends BaseDTO {
    private Long usuarioId; // opcional
    private String nivel;
    private String asociacion;
}