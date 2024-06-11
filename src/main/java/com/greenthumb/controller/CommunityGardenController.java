package com.greenthumb.controller;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.service.CommunityGardenService;
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
public class CommunityGardenController {

    private final CommunityGardenService communityGardenService;

    @Autowired
    public CommunityGardenController(CommunityGardenService communityGardenService) {
        this.communityGardenService = communityGardenService;
    }


    @Operation(summary = "Get all community gardens", description = "Retrieve a list of all community gardens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of community gardens retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CommunityGardenDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/communityGardens")
    public ResponseEntity<List<CommunityGardenDTO>> getAllCommunityGardens() {
        List<CommunityGardenDTO> gardens = communityGardenService.findAll();
        return ResponseEntity.ok(gardens);
    }

    @Operation(summary = "Get community garden by ID", description = "Retrieve a community garden by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community garden retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CommunityGardenDTO.class))),
            @ApiResponse(responseCode = "404", description = "Community garden not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/user/communityGardens/{id}")
    public ResponseEntity<CommunityGardenDTO> getCommunityGardenById(@PathVariable Long id) {
        return ResponseEntity.ok(communityGardenService.findById(id));
    }

    @Operation(summary = "Create a new community garden", description = "Create a new community garden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community garden created successfully",
                    content = @Content(schema = @Schema(implementation = CommunityGardenDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/communityGardens")
    public ResponseEntity<CommunityGardenDTO> createCommunityGarden(@RequestBody CommunityGardenDTO communityGardenDTO) {
        CommunityGardenDTO created = communityGardenService.create(communityGardenDTO);
        return ResponseEntity.ok(created);
    }


    @Operation(summary = "Update a community garden", description = "Update an existing community garden by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community garden updated successfully",
                    content = @Content(schema = @Schema(implementation = CommunityGardenDTO.class))),
            @ApiResponse(responseCode = "404", description = "Community garden not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/communityGardens")
    public ResponseEntity<CommunityGardenDTO> updateCommunityGarden(@PathVariable Long id, @RequestBody CommunityGardenDTO communityGardenDTO) {
        CommunityGardenDTO updated = communityGardenService.update(id, communityGardenDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a community garden", description = "Delete a community garden by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community garden deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Community garden not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/admin/{id}/communityGardens")
    public ResponseEntity<Void> deleteCommunityGarden(@PathVariable Long id) {
        communityGardenService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add user to a community garden", description = "Add a user to a community garden by their IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User added to community garden successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Community garden or user not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/{gardenId}/users/{userId}/communityGardens")
    public ResponseEntity<Void> addUserToCommunityGarden(
            @PathVariable Long gardenId,
            @PathVariable Long userId
    ) {
        communityGardenService.addUserToCommunityGarden(gardenId, userId);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Get community garden with users", description = "Retrieve a community garden along with its associated users by the garden ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community garden with users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CommunityGardenDTO.class))),
            @ApiResponse(responseCode = "404", description = "Community garden not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/admin/{gardenId}/users/communityGardens")
    public ResponseEntity<CommunityGardenDTO> getCommunityGardenWithUsers(@PathVariable Long gardenId) {
        return ResponseEntity.ok(communityGardenService.getCommunityGardenWithUsers(gardenId));
    }
}
