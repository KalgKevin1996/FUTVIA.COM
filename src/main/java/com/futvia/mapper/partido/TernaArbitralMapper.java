package com.futvia.mapper.partido;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.partido.*;
import com.futvia.dto.partido.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface TernaArbitralMapper {
    TernaArbitralDTO toDto(TernaArbitral e);
    TernaArbitral toEntity(TernaArbitralFormDTO f);
}