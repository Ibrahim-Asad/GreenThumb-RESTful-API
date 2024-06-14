package com.greenthumb.controller;


import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.model.dto.VolunteerDTO;
import com.greenthumb.service.VolunteerService;
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
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @Operation(summary = "Get all volunteers", description = "Retrieve a list of all volunteers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of volunteers retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/volunteers")
    public List<VolunteerDTO> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @Operation(summary = "Get a volunteer by ID", description = "Retrieve a volunteer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Volunteer not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/{id}/volunteers")
    public ResponseEntity<VolunteerDTO> getVolunteerById(@PathVariable Long id) {
        VolunteerDTO volunteerDTO = volunteerService.getVolunteerById(id);
        return volunteerDTO != null ? ResponseEntity.ok(volunteerDTO) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new volunteer", description = "Create a new volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/volunteers")
    public VolunteerDTO createVolunteer(@RequestBody VolunteerDTO volunteerDTO) {
        return volunteerService.createVolunteer(volunteerDTO);
    }

    @Operation(summary = "Update a volunteer", description = "Update an existing volunteer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Volunteer not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/volunteers")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable Long id, @RequestBody VolunteerDTO volunteerDTO) {
        VolunteerDTO updatedVolunteer = volunteerService.updateVolunteer(id, volunteerDTO);
        return updatedVolunteer != null ? ResponseEntity.ok(updatedVolunteer) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a volunteer", description = "Delete a volunteer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Volunteer deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Volunteer not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/admin/{id}/volunteers")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        boolean isDeleted = volunteerService.deleteVolunteer(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Schedule activity for volunteer", description = "Schedule an activity for a volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity scheduled successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Volunteer or activity not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/volunteer/{id}/schedule/{activityId}/volunteers")
    public ResponseEntity<VolunteerDTO> scheduleActivityForVolunteer(@PathVariable Long id, @PathVariable Long activityId) {
        VolunteerDTO volunteerDTO = volunteerService.scheduleActivityForVolunteer(id, activityId);
        return volunteerDTO != null ? ResponseEntity.ok(volunteerDTO) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get activities for volunteer", description = "Retrieve activities scheduled for a volunteer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of activities retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerActivityDTO.class))),
            @ApiResponse(responseCode = "404", description = "Volunteer not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/{id}/activities/volunteers")
    public ResponseEntity<List<VolunteerActivityDTO>> getActivitiesForVolunteer(@PathVariable Long id) {
        List<VolunteerActivityDTO> activities = volunteerService.getActivitiesForVolunteer(id);
        return activities != null ? ResponseEntity.ok(activities) : ResponseEntity.notFound().build();
    }
}
