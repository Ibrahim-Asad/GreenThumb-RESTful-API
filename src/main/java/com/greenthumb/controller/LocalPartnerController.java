package com.greenthumb.controller;


import com.greenthumb.model.dto.KnowledgeResourceDTO;
import com.greenthumb.model.dto.LocalPartnerDTO;
import com.greenthumb.service.LocalPartnerService;
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
public class LocalPartnerController {

    private final LocalPartnerService localPartnerService;

    @Autowired
    public LocalPartnerController(LocalPartnerService localPartnerService) {
        this.localPartnerService = localPartnerService;
    }


    @Operation(summary = "Get all knowledge resources", description = "Retrieve a list of all knowledge resources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of knowledge resources retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/user/localPartners")
    public ResponseEntity<List<LocalPartnerDTO>> getAllLocalPartners() {
        List<LocalPartnerDTO> partners = localPartnerService.findAll();
        return ResponseEntity.ok(partners);
    }

    @Operation(summary = "Get knowledge resource by ID", description = "Retrieve a knowledge resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Knowledge resource retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Knowledge resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/user/{id}/localPartners")
    public ResponseEntity<LocalPartnerDTO> getLocalPartnerById(@PathVariable Long id) {
        LocalPartnerDTO partner = localPartnerService.findById(id);
        return ResponseEntity.ok(partner);
    }


    @Operation(summary = "Create a new knowledge resource", description = "Create a new knowledge resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Knowledge resource created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/localPartners")
    public ResponseEntity<LocalPartnerDTO> createLocalPartner(@RequestBody LocalPartnerDTO localPartnerDTO) {
        LocalPartnerDTO newPartner = localPartnerService.create(localPartnerDTO);
        return ResponseEntity.ok(newPartner);
    }

    @Operation(summary = "Update a knowledge resource", description = "Update an existing knowledge resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Knowledge resource updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Knowledge resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/localPartners")
    public ResponseEntity<LocalPartnerDTO> updateLocalPartner(@PathVariable Long id, @RequestBody LocalPartnerDTO localPartnerDTO) {
        LocalPartnerDTO updatedPartner = localPartnerService.update(id, localPartnerDTO);
        return ResponseEntity.ok(updatedPartner);
    }

    @Operation(summary = "Delete a knowledge resource", description = "Delete a knowledge resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Knowledge resource deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Knowledge resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("/admin/{id}/localPartners")
    public ResponseEntity<Void> deleteLocalPartner(@PathVariable Long id) {
        localPartnerService.delete(id);
        return ResponseEntity.ok().build();
    }
}

