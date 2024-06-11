package com.greenthumb.controller;

import com.greenthumb.model.dto.SoilPestResourceDTO;
import com.greenthumb.service.SoilPestResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SoilPestResourceController {

    private SoilPestResourceService soilPestResourceService;

    @Autowired
    public SoilPestResourceController(SoilPestResourceService soilPestResourceService) {
        this.soilPestResourceService = soilPestResourceService;
    }


    @Operation(summary = "Get soil pest resource by ID", description = "Retrieve a soil pest resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soil pest resource retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoilPestResourceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Soil pest resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/soil-pest-resources/{id}")
    public ResponseEntity<SoilPestResourceDTO> getSoilPestResourceById(@PathVariable Long id) {
        SoilPestResourceDTO soilPestResourceDTO = soilPestResourceService.getSoilPestResourceById(id);
        return ResponseEntity.ok(soilPestResourceDTO);
    }

    @Operation(summary = "Get all soil pest resources", description = "Retrieve a list of all soil pest resources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of soil pest resources retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoilPestResourceDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/all-soil-pest-resources")
    public ResponseEntity<List<SoilPestResourceDTO>> getAllSoilPestResources() {
        List<SoilPestResourceDTO> soilPestResources = soilPestResourceService.getAllSoilPestResources();
        return ResponseEntity.ok(soilPestResources);
    }

    @Operation(summary = "Create a new soil pest resource", description = "Create a new soil pest resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soil pest resource created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoilPestResourceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/user/soil-pest-resources")
    public ResponseEntity<SoilPestResourceDTO> createSoilPestResource(@RequestBody SoilPestResourceDTO soilPestResourceDTO) {
        SoilPestResourceDTO createdResource = soilPestResourceService.createSoilPestResource(soilPestResourceDTO);
        return ResponseEntity.ok(createdResource);
    }

    @Operation(summary = "Update a soil pest resource", description = "Update an existing soil pest resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soil pest resource updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoilPestResourceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Soil pest resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/user/soil-pest-resources/{id}")
    public ResponseEntity<SoilPestResourceDTO> updateSoilPestResource(@PathVariable Long id, @RequestBody SoilPestResourceDTO soilPestResourceDTO) {
        SoilPestResourceDTO updatedResource = soilPestResourceService.updateSoilPestResource(id, soilPestResourceDTO);
        return ResponseEntity.ok(updatedResource);
    }

    @Operation(summary = "Delete a soil pest resource", description = "Delete a soil pest resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soil pest resource deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Soil pest resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/user/soil-pest-resources/{id}")
    public ResponseEntity<Void> deleteSoilPestResource(@PathVariable Long id) {
        soilPestResourceService.deleteSoilPestResource(id);
        return ResponseEntity.noContent().build();
    }
}
