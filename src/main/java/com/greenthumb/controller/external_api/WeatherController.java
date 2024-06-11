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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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


    @Operation(summary = "Get current weather by latitude and longitude", description = "Retrieves the current weather information based on latitude and longitude")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data retrieved successfully",
                    content = @Content(schema = @Schema(implementation = WeatherResourceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/current-weather/latlon")
    public ResponseEntity<WeatherResourceDTO> getCurrentWeatherByLatLon(
            @RequestParam("lat") double latitude,
            @RequestParam("lon") double longitude) {
        WeatherResourceDTO weatherData = weatherService.getCurrentWeatherByLatLon(latitude, longitude);
        return ResponseEntity.ok(weatherData);
    }


    @Operation(summary = "Get current weather by city name", description = "Retrieves the current weather information based on city name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data retrieved successfully",
                    content = @Content(schema = @Schema(implementation = WeatherResourceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/current-weather/city")
    public ResponseEntity<WeatherResourceDTO> getCurrentWeatherByCity(
            @RequestParam("city") String cityName) {
        WeatherResourceDTO weatherData = weatherService.getCurrentWeatherByCity(cityName);
        return ResponseEntity.ok(weatherData);
    }


    @Operation(summary = "Save weather data", description = "Saves the current weather information for a specific community garden based on latitude and longitude")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data saved successfully",
                    content = @Content(schema = @Schema(implementation = WeatherResource.class))),
            @ApiResponse(responseCode = "404", description = "Community garden not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
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

    @Operation(summary = "Get weather data for a community garden", description = "Retrieves the weather information for a specific community garden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data retrieved successfully",
                    content = @Content(schema = @Schema(implementation = WeatherResourceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Community garden or weather data not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/community-garden-weather")
    public ResponseEntity<Optional<WeatherResourceDTO>> getWeatherDataForCommunityGarden(@RequestParam("gardenId") Long gardenId) {
        Optional<WeatherResourceDTO> weatherData = weatherService.getWeatherDataForCommunityGarden(gardenId);
        return ResponseEntity.ok(weatherData);
    }

}
