package com.futvia.mapper.club;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.club.*;
import com.futvia.dto.club.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface ClubMapper {
    @Mappings({
            @Mapping(target = "municipioId", source = "municipio.id"),
            @Mapping(target = "municipioNombre", source = "municipio.nombre")
    })
    ClubDTO toDto(Club e);

    @Mapping(target = "municipio", source = "municipioId")
    Club toEntity(ClubFormDTO f);

    void update(@MappingTarget Club e, ClubFormDTO f);
}
