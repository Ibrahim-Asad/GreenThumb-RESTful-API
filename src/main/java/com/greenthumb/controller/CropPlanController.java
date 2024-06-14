package com.greenthumb.controller;



import com.greenthumb.model.dto.CropPlanDTO;
import com.greenthumb.service.CropPlanService;
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
public class CropPlanController {

    private final CropPlanService cropPlanService;

    @Autowired
    public CropPlanController(CropPlanService cropPlanService) {
        this.cropPlanService = cropPlanService;
    }


    @Operation(summary = "Get all crop plans", description = "Retrieve a list of all crop plans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of crop plans retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CropPlanDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/user")
    public ResponseEntity<List<CropPlanDTO>> getAllCropPlans() {
        return ResponseEntity.ok(cropPlanService.findAll());
    }

    @Operation(summary = "Get crop plan by ID", description = "Retrieve a crop plan by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crop plan retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CropPlanDTO.class))),
            @ApiResponse(responseCode = "404", description = "Crop plan not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/user/{id}/cropPlans")
    public ResponseEntity<CropPlanDTO> getCropPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(cropPlanService.findById(id));
    }


    @Operation(summary = "Create a new crop plan", description = "Create a new crop plan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crop plan created successfully",
                    content = @Content(schema = @Schema(implementation = CropPlanDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/cropPlans")
    public ResponseEntity<CropPlanDTO> createCropPlan(@RequestBody CropPlanDTO cropPlanDTO) {
        CropPlanDTO newCropPlan = cropPlanService.create(cropPlanDTO);
        return ResponseEntity.ok(newCropPlan);
    }


    @Operation(summary = "Update a crop plan", description = "Update an existing crop plan by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crop plan updated successfully",
                    content = @Content(schema = @Schema(implementation = CropPlanDTO.class))),
            @ApiResponse(responseCode = "404", description = "Crop plan not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/cropPlans")
    public ResponseEntity<CropPlanDTO> updateCropPlan(@PathVariable Long id, @RequestBody CropPlanDTO cropPlanDTO) {
        CropPlanDTO updatedCropPlan = cropPlanService.update(id, cropPlanDTO);
        return ResponseEntity.ok(updatedCropPlan);
    }

    @Operation(summary = "Delete a crop plan", description = "Delete a crop plan by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crop plan deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Crop plan not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/admin/{id}/cropPlans")
    public ResponseEntity<Void> deleteCropPlan(@PathVariable Long id) {
        cropPlanService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Schedule a crop plan", description = "Schedule a crop plan for a user on a specific plot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crop plan scheduled successfully",
                    content = @Content(schema = @Schema(implementation = CropPlanDTO.class))),
            @ApiResponse(responseCode = "404", description = "User or plot not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/user/schedule/cropPlans")
    public ResponseEntity<CropPlanDTO> scheduleCropPlan(
            @RequestParam Long userId,
            @RequestParam Long plotId,
            @RequestBody CropPlanDTO cropPlanDTO
    )
    {
        CropPlanDTO scheduledCropPlan = cropPlanService.scheduleCropPlan(userId, plotId, cropPlanDTO);
        return ResponseEntity.ok(scheduledCropPlan);
    }
}

