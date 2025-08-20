package com.futvia.mapper.competicion;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.competicion.*;
import com.futvia.dto.competicion.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface CategoriaMapper {
    @Mappings({
            @Mapping(target = "competicionId", source = "competicion.id"),
            @Mapping(target = "competicionNombre", source = "competicion.nombre")
    })
    CategoriaDTO toDto(Categoria e);

    @Mapping(target = "competicion", source = "competicionId")
    Categoria toEntity(CategoriaFormDTO f);

    void update(@MappingTarget Categoria e, CategoriaFormDTO f);
}