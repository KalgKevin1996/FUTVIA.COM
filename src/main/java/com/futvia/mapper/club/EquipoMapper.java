package com.futvia.mapper.club;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.club.*;
import com.futvia.dto.club.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface EquipoMapper {
    @Mappings({
            @Mapping(target = "clubId", source = "club.id"),
            @Mapping(target = "clubNombre", source = "club.nombre"),
            @Mapping(target = "competicionId", source = "competicion.id"),
            @Mapping(target = "competicionNombre", source = "competicion.nombre"),
            @Mapping(target = "categoriaId", source = "categoria.id"),
            @Mapping(target = "categoriaNombre", source = "categoria.nombre")
    })
    EquipoDTO toDto(Equipo e);

    @Mappings({
            @Mapping(target = "club", source = "clubId"),
            @Mapping(target = "competicion", source = "competicionId"),
            @Mapping(target = "categoria", source = "categoriaId")
    })
    Equipo toEntity(EquipoFormDTO f);

    void update(@MappingTarget Equipo e, EquipoFormDTO f);
}
