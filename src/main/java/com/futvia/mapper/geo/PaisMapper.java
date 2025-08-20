package com.futvia.mapper.geo;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.geo.*;
import com.futvia.dto.geo.*;

@Mapper(config = MapStructConfig.class)
public interface PaisMapper {
    PaisDTO toDto(Pais e);
    Pais toEntity(PaisFormDTO f);
    void update(@MappingTarget Pais e, PaisFormDTO f);
}