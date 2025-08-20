package com.futvia.mapper.contenido;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.contenido.*;
import com.futvia.dto.contenido.*;

@Mapper(config = MapStructConfig.class)
public interface ArchivoMapper {
    ArchivoDTO toDto(Archivo e);
    Archivo toEntity(ArchivoFormDTO f);
    void update(@MappingTarget Archivo e, ArchivoFormDTO f);
}