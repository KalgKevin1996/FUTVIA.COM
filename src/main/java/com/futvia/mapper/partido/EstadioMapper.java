package com.futvia.mapper.partido;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.partido.*;
import com.futvia.dto.partido.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface EstadioMapper {
    @Mappings({
            @Mapping(target = "municipioId", source = "municipio.id"),
            @Mapping(target = "municipioNombre", source = "municipio.nombre")
    })
    EstadioDTO toDto(Estadio e);

    @Mapping(target = "municipio", source = "municipioId")
    Estadio toEntity(EstadioFormDTO f);

    void update(@MappingTarget Estadio e, EstadioFormDTO f);
}