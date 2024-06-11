package com.greenthumb.controller;


import com.greenthumb.model.dto.ResourceExchangeDTO;
import com.greenthumb.service.ResourceExchangeService;
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
public class ResourceExchangeController {

    private final ResourceExchangeService resourceExchangeService;

    @Autowired
    public ResourceExchangeController(ResourceExchangeService resourceExchangeService) {
        this.resourceExchangeService = resourceExchangeService;
    }

    @Operation(summary = "Get all resource exchanges", description = "Retrieve a list of all resource exchanges")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of resource exchanges retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResourceExchangeDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/resourceExchanges")
    public ResponseEntity<List<ResourceExchangeDTO>> getAllResourceExchanges() {
        List<ResourceExchangeDTO> exchanges = resourceExchangeService.findAll();
        return ResponseEntity.ok(exchanges);
    }

    @Operation(summary = "Get resource exchange by ID", description = "Retrieve a resource exchange by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource exchange retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResourceExchangeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource exchange not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/volunteer/{id}/resourceExchanges")
    public ResponseEntity<ResourceExchangeDTO> getResourceExchangeById(@PathVariable Long id) {
        ResourceExchangeDTO exchange = resourceExchangeService.findById(id);
        return ResponseEntity.ok(exchange);
    }

    @Operation(summary = "Create a new resource exchange", description = "Create a new resource exchange")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource exchange created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResourceExchangeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/admin/resourceExchanges")
    public ResponseEntity<ResourceExchangeDTO> createResourceExchange(@RequestBody ResourceExchangeDTO resourceExchangeDTO) {
        ResourceExchangeDTO newExchange = resourceExchangeService.create(resourceExchangeDTO);
        return ResponseEntity.ok(newExchange);
    }

    @Operation(summary = "Update a resource exchange", description = "Update an existing resource exchange by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource exchange updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResourceExchangeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource exchange not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/admin/{id}/resourceExchanges")
    public ResponseEntity<ResourceExchangeDTO> updateResourceExchange(@PathVariable Long id, @RequestBody ResourceExchangeDTO resourceExchangeDTO) {
        ResourceExchangeDTO updatedExchange = resourceExchangeService.update(id, resourceExchangeDTO);
        return ResponseEntity.ok(updatedExchange);
    }

    @Operation(summary = "Delete a resource exchange", description = "Delete a resource exchange by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource exchange deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource exchange not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping("admin/{id}/resourceExchanges")
    public ResponseEntity<Void> deleteResourceExchange(@PathVariable Long id) {
        resourceExchangeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Decrement resource quantity in an exchange", description = "Decrement the quantity of a resource in a resource exchange")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource quantity decremented successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResourceExchangeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/volunteer/decrementQuantity/resourceExchanges")
    public ResponseEntity<ResourceExchangeDTO> decrementQuantity(
            @RequestParam Long resourceId, @RequestParam Long userId, @RequestParam int amount
    ) {
        ResourceExchangeDTO updatedResourceExchange = resourceExchangeService.decrementQuantity(resourceId, userId, amount);
        return ResponseEntity.ok(updatedResourceExchange);
    }

    @Operation(summary = "Increment resource quantity in an exchange", description = "Increment the quantity of a resource in a resource exchange")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource quantity incremented successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResourceExchangeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/volunteer/incrementQuantity/resourceExchanges")
    public ResponseEntity<ResourceExchangeDTO> incrementQuantity(
            @RequestParam Long resourceId, @RequestParam Long userId, @RequestParam int amount
    ) {
        ResourceExchangeDTO updatedResourceExchange = resourceExchangeService.incrementQuantity(resourceId, userId, amount);
        return ResponseEntity.ok(updatedResourceExchange);
    }
}

