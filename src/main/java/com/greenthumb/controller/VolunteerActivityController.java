package com.greenthumb.controller;

import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.service.VolunteerActivityService;
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
public class VolunteerActivityController {

    @Autowired
    private VolunteerActivityService volunteerActivityService;

    @Operation(summary = "Get all volunteer activities", description = "Retrieve a list of all volunteer activities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of volunteer activities retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerActivityDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/volunteer-activities")
    public List<VolunteerActivityDTO> getAllVolunteerActivities() {
        return volunteerActivityService.getAllVolunteerActivities();
    }

    @Operation(summary = "Get a volunteer activity by ID", description = "Retrieve a volunteer activity by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer activity retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerActivityDTO.class))),
            @ApiResponse(responseCode = "404", description = "Volunteer activity not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/{id}/volunteer-activities")
    public ResponseEntity<VolunteerActivityDTO> getVolunteerActivityById(@PathVariable Long id) {
        VolunteerActivityDTO volunteerActivityDTO = volunteerActivityService.getVolunteerActivityById(id);
        return volunteerActivityDTO != null ? ResponseEntity.ok(volunteerActivityDTO) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new volunteer activity", description = "Create a new volunteer activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer activity created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerActivityDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/volunteer-activities")
    public VolunteerActivityDTO createVolunteerActivity(@RequestBody VolunteerActivityDTO volunteerActivityDTO) {
        return volunteerActivityService.createVolunteerActivity(volunteerActivityDTO);
    }

    @Operation(summary = "Update a volunteer activity", description = "Update an existing volunteer activity by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer activity updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerActivityDTO.class))),
            @ApiResponse(responseCode = "404", description = "Volunteer activity not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/volunteer-activities")
    public ResponseEntity<VolunteerActivityDTO> updateVolunteerActivity(@PathVariable Long id, @RequestBody VolunteerActivityDTO volunteerActivityDTO) {
        VolunteerActivityDTO updatedVolunteerActivity = volunteerActivityService.updateVolunteerActivity(id, volunteerActivityDTO);
        return updatedVolunteerActivity != null ? ResponseEntity.ok(updatedVolunteerActivity) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a volunteer activity", description = "Delete a volunteer activity by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Volunteer activity deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Volunteer activity not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/admin/{id}/volunteer-activities")
    public ResponseEntity<Void> deleteVolunteerActivity(@PathVariable Long id) {
        boolean isDeleted = volunteerActivityService.deleteVolunteerActivity(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
