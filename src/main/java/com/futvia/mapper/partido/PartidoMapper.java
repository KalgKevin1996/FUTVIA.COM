package com.futvia.mapper.partido;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.partido.*;
import com.futvia.dto.partido.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface PartidoMapper {
    @Mappings({
            @Mapping(target = "temporadaId", source = "temporada.id"),
            @Mapping(target = "temporadaNombre", source = "temporada.nombre"),
            @Mapping(target = "categoriaId", source = "categoria.id"),
            @Mapping(target = "categoriaNombre", source = "categoria.nombre"),
            @Mapping(target = "jornadaId", source = "jornada.id"),
            @Mapping(target = "jornadaNumero", source = "jornada.numero"),
            @Mapping(target = "estadioId", source = "estadio.id"),
            @Mapping(target = "estadioNombre", source = "estadio.nombre"),
            @Mapping(target = "equipoLocalId", source = "equipoLocal.id"),
            @Mapping(target = "equipoLocalNombre", source = "equipoLocal.nombreVisible"),
            @Mapping(target = "equipoVisitanteId", source = "equipoVisitante.id"),
            @Mapping(target = "equipoVisitanteNombre", source = "equipoVisitante.nombreVisible")
    })
    PartidoDTO toDto(Partido e);

    @Mappings({
            @Mapping(target = "temporada", source = "temporadaId"),
            @Mapping(target = "categoria", source = "categoriaId"),
            @Mapping(target = "jornada", source = "jornadaId"),
            @Mapping(target = "estadio", source = "estadioId"),
            @Mapping(target = "equipoLocal", source = "equipoLocalId"),
            @Mapping(target = "equipoVisitante", source = "equipoVisitanteId")
    })
    Partido toEntity(PartidoFormDTO f);

    void update(@MappingTarget Partido e, PartidoFormDTO f);
}