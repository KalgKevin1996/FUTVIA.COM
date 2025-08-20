package com.futvia.mapper.disciplina;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.disciplina.*;
import com.futvia.dto.disciplina.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface SancionMapper {
    @Mappings({
            @Mapping(target = "jugadorId", source = "jugador.id"),
            @Mapping(target = "jugadorNombre", expression = "java(ref.nombreCompleto(e.getJugador()))"),
            @Mapping(target = "equipoId", source = "equipo.id"),
            @Mapping(target = "equipoNombre", source = "equipo.nombreVisible"),
            @Mapping(target = "origenReporteId", source = "origenReporte.id")
    })
    SancionDTO toDto(Sancion e, @Context RefMapper ref);

    @Mappings({
            @Mapping(target = "jugador", source = "jugadorId"),
            @Mapping(target = "equipo", source = "equipoId"),
            @Mapping(target = "origenReporte", source = "origenReporteId")
    })
    Sancion toEntity(SancionFormDTO f);

    void update(@MappingTarget Sancion e, SancionFormDTO f);
}