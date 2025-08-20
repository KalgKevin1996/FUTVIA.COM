package com.futvia.mapper.geo;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.geo.*;
import com.futvia.dto.geo.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface MunicipioMapper {
    @Mappings({
            @Mapping(target = "departamentoId", source = "departamento.id"),
            @Mapping(target = "departamentoNombre", source = "departamento.nombre")
    })
    MunicipioDTO toDto(Municipio e);

    @Mapping(target = "departamento", source = "departamentoId")
    Municipio toEntity(MunicipioFormDTO f);

    void update(@MappingTarget Municipio e, MunicipioFormDTO f);
}
