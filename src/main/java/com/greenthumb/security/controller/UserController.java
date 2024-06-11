package com.greenthumb.security.controller;


import com.greenthumb.security.model.dto.UserCreateDTO;
import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.model.dto.UserUpdateDTO;
import com.greenthumb.security.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users", description = "Retrieves a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    @GetMapping
    public List<UserDTO> getAllUsers() {
            return userService.getAllUsers();
    }


    @Operation(summary = "Get a user by ID", description = "Retrieves a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.getUserById(id);
            return ResponseEntity.ok(userDTO);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @Operation(summary = "Create a new user", description = "Creates a new user and returns the user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserDTO createdUser = userService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @Operation(summary = "Update a user", description = "Updates an existing user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO updatedUser = userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }


    @Operation(summary = "Delete a user", description = "Deletes a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
