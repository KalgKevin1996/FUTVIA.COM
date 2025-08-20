package com.futvia.mapper.partido;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.partido.*;
import com.futvia.dto.partido.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface JornadaMapper {
    @Mappings({
            @Mapping(target = "temporadaId", source = "temporada.id"),
            @Mapping(target = "temporadaNombre", source = "temporada.nombre"),
            @Mapping(target = "categoriaId", source = "categoria.id"),
            @Mapping(target = "categoriaNombre", source = "categoria.nombre")
    })
    JornadaDTO toDto(Jornada e);

    @Mappings({
            @Mapping(target = "temporada", source = "temporadaId"),
            @Mapping(target = "categoria", source = "categoriaId")
    })
    Jornada toEntity(JornadaFormDTO f);

    void update(@MappingTarget Jornada e, JornadaFormDTO f);
}
