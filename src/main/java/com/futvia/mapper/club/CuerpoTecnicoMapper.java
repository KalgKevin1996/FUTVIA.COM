package com.futvia.mapper.club;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.club.*;
import com.futvia.dto.club.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface CuerpoTecnicoMapper {
    @Mappings({
            @Mapping(target = "equipoId", source = "equipo.id"),
            @Mapping(target = "equipoNombre", source = "equipo.nombreVisible"),
            @Mapping(target = "usuarioId", source = "usuario.id")
    })
    CuerpoTecnicoDTO toDto(CuerpoTecnico e);

    @Mappings({
            @Mapping(target = "equipo", source = "equipoId"),
            @Mapping(target = "usuario", source = "usuarioId")
    })
    CuerpoTecnico toEntity(CuerpoTecnicoFormDTO f);

    void update(@MappingTarget CuerpoTecnico e, CuerpoTecnicoFormDTO f);
}