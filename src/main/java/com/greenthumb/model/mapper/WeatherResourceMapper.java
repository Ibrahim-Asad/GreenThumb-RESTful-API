package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.WeatherResourceDTO;
import com.greenthumb.model.entity.WeatherResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeatherResourceMapper {

    WeatherResourceMapper INSTANCE = Mappers.getMapper(WeatherResourceMapper.class);

    WeatherResource toWeatherResource(WeatherResourceDTO weatherResourceDTO);
    WeatherResourceDTO toWeatherResourceDTO(WeatherResource weatherResource);
}
