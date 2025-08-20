package com.futvia.mapper.club;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.club.*;
import com.futvia.dto.club.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface ArbitroMapper {
    @Mapping(target = "usuarioId", source = "usuario.id")
    ArbitroDTO toDto(Arbitro e);

    @Mapping(target = "usuario", source = "usuarioId")
    Arbitro toEntity(ArbitroFormDTO f);

    void update(@MappingTarget Arbitro e, ArbitroFormDTO f);
}
