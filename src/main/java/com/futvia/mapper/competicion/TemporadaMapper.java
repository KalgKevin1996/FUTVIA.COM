package com.futvia.mapper.competicion;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.competicion.*;
import com.futvia.dto.competicion.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface TemporadaMapper {
    @Mappings({
            @Mapping(target = "competicionId", source = "competicion.id"),
            @Mapping(target = "competicionNombre", source = "competicion.nombre")
    })
    TemporadaDTO toDto(Temporada e);

    @Mapping(target = "competicion", source = "competicionId")
    Temporada toEntity(TemporadaFormDTO f);

    void update(@MappingTarget Temporada e, TemporadaFormDTO f);
}