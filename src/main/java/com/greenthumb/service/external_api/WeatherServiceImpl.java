package com.greenthumb.service.external_api;

import com.greenthumb.model.dto.CommunityGardenDTos;
import com.greenthumb.model.dto.WeatherResourceDTO;
import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.model.entity.WeatherResource;
import com.greenthumb.model.mapper.CommunityGardenMapper;
import com.greenthumb.model.mapper.WeatherResourceMapper;
import com.greenthumb.repository.CommunityGardenRepo;
import com.greenthumb.repository.WeatherResourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherResourceRepo weatherResourceRepo;
    private final CommunityGardenRepo communityGardenRepo;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate, WeatherResourceRepo weatherResourceRepo,
                              CommunityGardenRepo communityGardenRepo) {
        this.restTemplate = restTemplate;
        this.weatherResourceRepo = weatherResourceRepo;
        this.communityGardenRepo = communityGardenRepo;
    }

    @Override
    public WeatherResource saveWeatherData(double latitude, double longitude, CommunityGardenDTos communityGarden) {
        try {
            String apiUrl = buildApiUrl(latitude, longitude);
            WeatherResource weatherResource = fetchAndSaveWeatherData(apiUrl, communityGarden);
            return weatherResource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<WeatherResourceDTO> getWeatherDataForCommunityGarden(Long gardenId) {
        CommunityGarden communityGarden = communityGardenRepo.findById(gardenId)
                .orElseThrow(() -> new RuntimeException("Community garden not found with id: " + gardenId));
        Optional<WeatherResource> weatherResource = Optional.ofNullable(weatherResourceRepo.findByCommunityGarden(communityGarden)
                .orElseThrow(() -> new RuntimeException("Weather data not found for community garden with id: " + gardenId)));
        return WeatherResourceMapper.INSTANCE.toOptionalWeatherResourceDTO(weatherResource);
    }

    @Override
    public WeatherResourceDTO getCurrentWeatherByCity(String cityName) {
        String apiUrl = buildApiUrl(cityName);
        return fetchWeatherData(apiUrl);
    }

    @Override
    public WeatherResourceDTO getCurrentWeatherByLatLon(double latitude, double longitude) {
        String apiUrl = buildApiUrl(latitude, longitude);
        return fetchWeatherData(apiUrl);
    }


    private String buildApiUrl(String city) {
        return UriComponentsBuilder
                .fromUriString("https://open-weather13.p.rapidapi.com/city/" + city + "/EN")
                .build()
                .toString();
    }

    private String buildApiUrl(double latitude, double longitude) {
        return UriComponentsBuilder
                .fromUriString("https://open-weather13.p.rapidapi.com/city/latlon/" + latitude + "/" + longitude)
                .build()
                .toString();
    }

    private String buildForecastApiUrl(double latitude, double longitude) {
        return UriComponentsBuilder
                .fromUriString("https://open-weather13.p.rapidapi.com/forecast/latlon/" + latitude + "/" + longitude)
                .build()
                .toString();
    }

    private WeatherResourceDTO fetchWeatherData(String apiUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", "open-weather13.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<WeatherResourceDTO> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, WeatherResourceDTO.class);
        return response.getBody();
    }

    private WeatherResource fetchAndSaveWeatherData(String apiUrl, CommunityGardenDTos communityGarden) {
        WeatherResourceDTO weatherResourceDTO = fetchWeatherData(apiUrl);

        if (weatherResourceDTO == null) {
            throw new RuntimeException("Failed to fetch weather data from API");
        }

        WeatherResource weatherResource = WeatherResource.builder()
                .latitude(weatherResourceDTO.getCoord().getLat())
                .longitude(weatherResourceDTO.getCoord().getLon())
                .cityName(weatherResourceDTO.getName())
                .country(weatherResourceDTO.getSys().getCountry())
                .temperature(convertFahrenheitToCelsius(weatherResourceDTO.getMain().getTemp()))
                .feelsLike(convertFahrenheitToCelsius(weatherResourceDTO.getMain().getFeels_like()))
                .tempMin(convertFahrenheitToCelsius(weatherResourceDTO.getMain().getTemp_min()))
                .tempMax(convertFahrenheitToCelsius(weatherResourceDTO.getMain().getTemp_max()))
                .pressure(weatherResourceDTO.getMain().getPressure())
                .humidity(weatherResourceDTO.getMain().getHumidity())
                .windSpeed(weatherResourceDTO.getWind().getSpeed())
                .windDegree(weatherResourceDTO.getWind().getDeg())
                .cloudiness(weatherResourceDTO.getClouds().getAll())
                .date(Date.from(Instant.ofEpochSecond(weatherResourceDTO.getDt())))
                .sunrise(Date.from(Instant.ofEpochSecond(weatherResourceDTO.getSys().getSunrise())))
                .sunset(Date.from(Instant.ofEpochSecond(weatherResourceDTO.getSys().getSunset())))
                .communityGarden(CommunityGardenMapper.INSTANCE.toCommunityGarden(communityGarden))
                .build();

        return weatherResourceRepo.save(weatherResource);
    }
    private double convertFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

}
