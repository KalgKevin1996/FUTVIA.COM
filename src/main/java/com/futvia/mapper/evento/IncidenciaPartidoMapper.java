package com.futvia.mapper.evento;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.evento.*;
import com.futvia.dto.evento.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface IncidenciaPartidoMapper {
    @Mappings({
            @Mapping(target = "partidoId", source = "partido.id"),
            @Mapping(target = "equipoId", source = "equipo.id"),
            @Mapping(target = "equipoNombre", source = "equipo.nombreVisible"),
            @Mapping(target = "jugadorPrincipalId", source = "jugadorPrincipal.id"),
            @Mapping(target = "jugadorPrincipalNombre", expression = "java(ref.nombreCompleto(e.getJugadorPrincipal()))"),
            @Mapping(target = "jugadorInvolucradoId", source = "jugadorInvolucrado.id"),
            @Mapping(target = "jugadorInvolucradoNombre", expression = "java(ref.nombreCompleto(e.getJugadorInvolucrado()))")
    })
    IncidenciaPartidoDTO toDto(IncidenciaPartido e, @Context RefMapper ref);

    @Mappings({
            @Mapping(target = "partido", source = "partidoId"),
            @Mapping(target = "equipo", source = "equipoId"),
            @Mapping(target = "jugadorPrincipal", source = "jugadorPrincipalId"),
            @Mapping(target = "jugadorInvolucrado", source = "jugadorInvolucradoId")
    })
    IncidenciaPartido toEntity(IncidenciaPartidoFormDTO f);

    void update(@MappingTarget IncidenciaPartido e, IncidenciaPartidoFormDTO f);
}