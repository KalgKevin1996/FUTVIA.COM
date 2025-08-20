package com.futvia.dto.club;


import lombok.*;

// Forms
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class ClubFormDTO { private String nombre; private String escudoUrl; private Long municipioId; }