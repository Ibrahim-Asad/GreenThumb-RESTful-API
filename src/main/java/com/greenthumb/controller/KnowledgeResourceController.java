package com.greenthumb.controller;



import com.greenthumb.model.dto.KnowledgeResourceDTO;
import com.greenthumb.service.KnowledgeResourceService;
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
public class KnowledgeResourceController {

    private final KnowledgeResourceService knowledgeResourceService;

    @Autowired
    public KnowledgeResourceController(KnowledgeResourceService knowledgeResourceService) {
        this.knowledgeResourceService = knowledgeResourceService;
    }

    @Operation(summary = "Get all knowledge resources", description = "Retrieve a list of all knowledge resources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of knowledge resources retrieved successfully",
                    content = @Content(schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/knowledgeResources")
    public ResponseEntity<List<KnowledgeResourceDTO>> getAllKnowledgeResources() {
        List<KnowledgeResourceDTO> resources = knowledgeResourceService.findAll();
        return ResponseEntity.ok(resources);
    }


    @Operation(summary = "Get knowledge resource by ID", description = "Retrieve a knowledge resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Knowledge resource retrieved successfully",
                    content = @Content(schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Knowledge resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/{id}/knowledgeResources")
    public ResponseEntity<KnowledgeResourceDTO> getKnowledgeResourceById(@PathVariable Long id) {
        KnowledgeResourceDTO resource = knowledgeResourceService.findById(id);
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Create a new knowledge resource", description = "Create a new knowledge resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Knowledge resource created successfully",
                    content = @Content(schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/knowledgeResources")
    public ResponseEntity<KnowledgeResourceDTO> createKnowledgeResource(@RequestBody KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResourceDTO newResource = knowledgeResourceService.create(knowledgeResourceDTO);
        return ResponseEntity.ok(newResource);
    }

    @Operation(summary = "Update a knowledge resource", description = "Update an existing knowledge resource by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Knowledge resource updated successfully",
                    content = @Content(schema = @Schema(implementation = KnowledgeResourceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Knowledge resource not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/knowledgeResources")
    public ResponseEntity<KnowledgeResourceDTO> updateKnowledgeResource(@PathVariable Long id, @RequestBody KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResourceDTO updatedResource = knowledgeResourceService.update(id, knowledgeResourceDTO);
        return ResponseEntity.ok(updatedResource);
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
    @DeleteMapping("/admin/{id}/knowledgeResources")
    public ResponseEntity<Void> deleteKnowledgeResource(@PathVariable Long id) {
        knowledgeResourceService.delete(id);
        return ResponseEntity.ok().build();
    }
}

