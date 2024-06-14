package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.WeatherResourceDTO;
import com.greenthumb.model.entity.WeatherResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface WeatherResourceMapper {

    WeatherResourceMapper INSTANCE = Mappers.getMapper(WeatherResourceMapper.class);

    @Mappings({
            @Mapping(source = "coord.lat", target = "latitude"),
            @Mapping(source = "coord.lon", target = "longitude"),
            @Mapping(source = "name", target = "cityName"),
            @Mapping(source = "sys.country", target = "country"),
            @Mapping(source = "main.temp", target = "temperature"),
            @Mapping(source = "main.feels_like", target = "feelsLike"),
            @Mapping(source = "main.temp_min", target = "tempMin"),
            @Mapping(source = "main.temp_max", target = "tempMax"),
            @Mapping(source = "main.pressure", target = "pressure"),
            @Mapping(source = "main.humidity", target = "humidity"),
            @Mapping(source = "wind.speed", target = "windSpeed"),
            @Mapping(source = "wind.deg", target = "windDegree"),
            @Mapping(source = "clouds.all", target = "cloudiness"),
            @Mapping(source = "dt", target = "date", qualifiedByName = "timestampToDate"),
            @Mapping(source = "sys.sunrise", target = "sunrise", qualifiedByName = "timestampToDate"),
            @Mapping(source = "sys.sunset", target = "sunset", qualifiedByName = "timestampToDate")
    })
    WeatherResource toWeatherResource(WeatherResourceDTO weatherResourceDTO);

    @Mappings({
            @Mapping(source = "latitude", target = "coord.lat"),
            @Mapping(source = "longitude", target = "coord.lon"),
            @Mapping(source = "cityName", target = "name"),
            @Mapping(source = "country", target = "sys.country"),
            @Mapping(source = "temperature", target = "main.temp"),
            @Mapping(source = "feelsLike", target = "main.feels_like"),
            @Mapping(source = "tempMin", target = "main.temp_min"),
            @Mapping(source = "tempMax", target = "main.temp_max"),
            @Mapping(source = "pressure", target = "main.pressure"),
            @Mapping(source = "humidity", target = "main.humidity"),
            @Mapping(source = "windSpeed", target = "wind.speed"),
            @Mapping(source = "windDegree", target = "wind.deg"),
            @Mapping(source = "cloudiness", target = "clouds.all"),
            @Mapping(source = "date", target = "dt", qualifiedByName = "dateToTimestamp"),
            @Mapping(source = "sunrise", target = "sys.sunrise", qualifiedByName = "dateToTimestamp"),
            @Mapping(source = "sunset", target = "sys.sunset", qualifiedByName = "dateToTimestamp")
    })
    WeatherResourceDTO toWeatherResourceDTO(WeatherResource weatherResource);

    default Optional<WeatherResourceDTO> toOptionalWeatherResourceDTO(Optional<WeatherResource> weatherResource) {
        return weatherResource.map(this::toWeatherResourceDTO);
    }

    default Optional<WeatherResource> toOptionalWeatherResource(Optional<WeatherResourceDTO> weatherResourceDTO) {
        return weatherResourceDTO.map(this::toWeatherResource);
    }

    @Named("timestampToDate")
    default Date timestampToDate(Long timestamp) {
        return timestamp != null ? new Date(timestamp * 1000) : null;
    }

    @Named("dateToTimestamp")
    default Long dateToTimestamp(Date date) {
        return date != null ? date.getTime() / 1000 : null;
    }
}