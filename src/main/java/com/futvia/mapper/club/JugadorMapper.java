package com.futvia.mapper.club;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.club.*;
import com.futvia.dto.club.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface JugadorMapper {
    @Mapping(target = "usuarioId", source = "usuario.id")
    JugadorDTO toDto(Jugador e);

    @Mapping(target = "usuario", source = "usuarioId")
    Jugador toEntity(JugadorFormDTO f);

    void update(@MappingTarget Jugador e, JugadorFormDTO f);
}
