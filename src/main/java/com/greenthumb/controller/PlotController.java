package com.greenthumb.controller;


import com.greenthumb.model.dto.PlotDTO;
import com.greenthumb.service.PlotService;
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
public class PlotController {

    private final PlotService plotService;

    @Autowired
    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @Operation(summary = "Get all plots", description = "Retrieve a list of all plots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of plots retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlotDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/plots")
    public ResponseEntity<List<PlotDTO>> getAllPlots() {
        List<PlotDTO> plots = plotService.findAll();
        return ResponseEntity.ok(plots);
    }

    @Operation(summary = "Get plot by ID", description = "Retrieve a plot by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plot retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlotDTO.class))),
            @ApiResponse(responseCode = "404", description = "Plot not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/{id}/plots")
    public ResponseEntity<PlotDTO> getPlotById(@PathVariable Long id) {
        PlotDTO plot = plotService.findById(id);
        return ResponseEntity.ok(plot);
    }

    @Operation(summary = "Create a new plot", description = "Create a new plot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plot created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlotDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/plots")
    public ResponseEntity<PlotDTO> createPlot(@RequestBody PlotDTO plotDTO) {
        PlotDTO newPlot = plotService.create(plotDTO);
        return ResponseEntity.ok(newPlot);
    }

    @Operation(summary = "Update a plot", description = "Update an existing plot by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plot updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlotDTO.class))),
            @ApiResponse(responseCode = "404", description = "Plot not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/plots")
    public ResponseEntity<PlotDTO> updatePlot(@PathVariable Long id, @RequestBody PlotDTO plotDTO) {
        PlotDTO updatedPlot = plotService.update(id, plotDTO);
        return ResponseEntity.ok(updatedPlot);
    }

    @Operation(summary = "Delete a plot", description = "Delete a plot by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plot deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Plot not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/admin/{id}/plots")
    public ResponseEntity<Void> deletePlot(@PathVariable Long id) {
        plotService.delete(id);
        return ResponseEntity.ok().build();
    }
}

