package com.futvia.mapper.club;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.club.*;
import com.futvia.dto.club.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface PlantillaMapper {
    @Mappings({
            @Mapping(target = "equipoId", source = "equipo.id"),
            @Mapping(target = "equipoNombre", source = "equipo.nombreVisible"),
            @Mapping(target = "jugadorId", source = "jugador.id"),
            @Mapping(target = "jugadorNombreCompleto", expression = "java(ref.nombreCompleto(e.getJugador()))"),
            @Mapping(target = "temporadaId", source = "temporada.id"),
            @Mapping(target = "temporadaNombre", source = "temporada.nombre")
    })
    PlantillaDTO toDto(Plantilla e, @Context RefMapper ref);

    @Mappings({
            @Mapping(target = "equipo", source = "equipoId"),
            @Mapping(target = "jugador", source = "jugadorId"),
            @Mapping(target = "temporada", source = "temporadaId")
    })
    Plantilla toEntity(PlantillaFormDTO f);

    void update(@MappingTarget Plantilla e, PlantillaFormDTO f);
}