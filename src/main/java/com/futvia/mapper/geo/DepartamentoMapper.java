package com.futvia.mapper.geo;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.geo.*;
import com.futvia.dto.geo.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface DepartamentoMapper {
    @Mappings({
            @Mapping(target = "paisId", source = "pais.id"),
            @Mapping(target = "paisNombre", source = "pais.nombre")
    })
    DepartamentoDTO toDto(Departamento e);

    @Mapping(target = "pais", source = "paisId")
    Departamento toEntity(DepartamentoFormDTO f);

    void update(@MappingTarget Departamento e, DepartamentoFormDTO f);
}