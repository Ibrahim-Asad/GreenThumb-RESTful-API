package com.greenthumb.security.controller;

import com.greenthumb.security.model.dto.RoleDTO;
import com.greenthumb.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Create a new role", description = "Creates a new role and returns the role details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created successfully",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleService.createRole(roleDTO);
        return ResponseEntity.ok(createdRole);
    }


    @Operation(summary = "Get a role by ID", description = "Retrieves a role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role retrieved successfully",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        RoleDTO roleDTO = roleService.getRoleById(id);
        return ResponseEntity.ok(roleDTO);
    }


    @Operation(summary = "Get all roles", description = "Retrieves a list of all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles retrieved successfully",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }


    @Operation(summary = "Update a role", description = "Updates an existing role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }


    @Operation(summary = "Delete a role", description = "Deletes a role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Add a role to a user", description = "Adds an existing role to an existing user by their IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role added to user successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Role or User not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/{roleId}/users/{userId}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable Long roleId, @PathVariable Long userId) {
        roleService.addRoleToUser(roleId, userId);
        return ResponseEntity.ok().build();
    }
}
