package com.futvia.mapper.evento;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.evento.*;
import com.futvia.dto.evento.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface EstadisticaEquipoPartidoMapper {
    @Mappings({
            @Mapping(target = "partidoId", source = "partido.id"),
            @Mapping(target = "equipoId", source = "equipo.id"),
            @Mapping(target = "equipoNombre", source = "equipo.nombreVisible")
    })
    EstadisticaEquipoPartidoDTO toDto(EstadisticaEquipoPartido e);

    @Mappings({
            @Mapping(target = "partido", source = "partidoId"),
            @Mapping(target = "equipo", source = "equipoId")
    })
    EstadisticaEquipoPartido toEntity(EstadisticaEquipoPartidoFormDTO f);

    void update(@MappingTarget EstadisticaEquipoPartido e, EstadisticaEquipoPartidoFormDTO f);
}