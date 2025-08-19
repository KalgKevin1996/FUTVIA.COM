package com.futvia.dto.club;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ArbitroFormDTO { private Long usuarioId; private String nivel; private String asociacion; }