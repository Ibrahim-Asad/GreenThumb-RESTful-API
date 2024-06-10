package com.greenthumb.service.external_api;

import com.greenthumb.model.dto.CommunityGardenDTos;
import com.greenthumb.model.dto.WeatherResourceDTO;
import com.greenthumb.model.entity.WeatherResource;

import java.util.Optional;

public interface WeatherService {
    WeatherResource saveWeatherData(double latitude, double longitude, CommunityGardenDTos communityGarden);
    Optional<WeatherResourceDTO> getWeatherDataForCommunityGarden(Long gardenId);
    WeatherResourceDTO getCurrentWeatherByCity(String cityName);
    WeatherResourceDTO getCurrentWeatherByLatLon(double latitude, double longitude);
}
