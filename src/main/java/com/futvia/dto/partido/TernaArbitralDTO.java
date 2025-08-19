package com.futvia.dto.partido;

import com.futvia.dto.common.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TernaArbitralDTO extends BaseDTO {
    private Long partidoId;
}