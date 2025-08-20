package com.futvia.mapper.competicion;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.competicion.*;
import com.futvia.dto.competicion.*;

@Mapper(config = MapStructConfig.class)
public interface OrganizadorMapper {
    OrganizadorDTO toDto(Organizador e);
    Organizador toEntity(OrganizadorFormDTO f);
    void update(@MappingTarget Organizador e, OrganizadorFormDTO f);
}