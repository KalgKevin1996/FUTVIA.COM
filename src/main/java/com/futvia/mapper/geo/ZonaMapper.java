package com.futvia.mapper.geo;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.geo.*;
import com.futvia.dto.geo.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface ZonaMapper {
    @Mappings({
            @Mapping(target = "municipioId", source = "municipio.id"),
            @Mapping(target = "municipioNombre", source = "municipio.nombre")
    })
    ZonaDTO toDto(Zona e);

    @Mapping(target = "municipio", source = "municipioId")
    Zona toEntity(ZonaFormDTO f);

    void update(@MappingTarget Zona e, ZonaFormDTO f);
}