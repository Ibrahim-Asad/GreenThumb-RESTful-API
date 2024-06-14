package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.SoilPestResourceDTO;
import com.greenthumb.model.entity.SoilPestResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SoilPestResourceMapper {

    SoilPestResourceMapper INSTANCE = Mappers.getMapper(SoilPestResourceMapper.class);

    SoilPestResource toSoilPestResource(SoilPestResourceDTO soilPestResourceDTO);
    SoilPestResourceDTO toSoilPestResourceDTO(SoilPestResource soilPestResource);
}
