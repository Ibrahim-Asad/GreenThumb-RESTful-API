package com.greenthumb.controller.external_api;

import com.greenthumb.model.dto.WeatherResourceDTO;
import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.model.entity.WeatherResource;
import com.greenthumb.model.mapper.CommunityGardenMapper;
import com.greenthumb.repository.CommunityGardenRepo;
import com.greenthumb.service.external_api.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class WeatherController {

    private final WeatherService weatherService;
    private final CommunityGardenRepo communityGardenRepo;

    @Autowired
    public WeatherController(WeatherService weatherService, CommunityGardenRepo communityGardenRepo) {
        this.weatherService = weatherService;
        this.communityGardenRepo = communityGardenRepo;
    }

    @GetMapping("/current-weather/latlon")
    public ResponseEntity<WeatherResourceDTO> getCurrentWeatherByLatLon(
            @RequestParam("lat") double latitude,
            @RequestParam("lon") double longitude) {
        WeatherResourceDTO weatherData = weatherService.getCurrentWeatherByLatLon(latitude, longitude);
        return ResponseEntity.ok(weatherData);
    }

    @GetMapping("/current-weather/city")
    public ResponseEntity<WeatherResourceDTO> getCurrentWeatherByCity(
            @RequestParam("city") String cityName) {
        WeatherResourceDTO weatherData = weatherService.getCurrentWeatherByCity(cityName);
        return ResponseEntity.ok(weatherData);
    }

    @PostMapping("/save-weather")
    public ResponseEntity<WeatherResource> saveWeatherData(
            @RequestParam("lat") double latitude,
            @RequestParam("lon") double longitude,
            @RequestParam("gardenId") Long gardenId) {
        CommunityGarden communityGarden = communityGardenRepo.findById(gardenId)
                .orElseThrow(() -> new RuntimeException("Community garden not found with id: " + gardenId));
        WeatherResource weatherResource = weatherService.saveWeatherData(latitude, longitude, CommunityGardenMapper.INSTANCE.toCommunityGardenDTos(communityGarden));
        return ResponseEntity.ok(weatherResource);
    }

    @GetMapping("/community-garden-weather")
    public ResponseEntity<Optional<WeatherResourceDTO>> getWeatherDataForCommunityGarden(@RequestParam("gardenId") Long gardenId) {
        Optional<WeatherResourceDTO> weatherData = weatherService.getWeatherDataForCommunityGarden(gardenId);
        return ResponseEntity.ok(weatherData);
    }

}
