package com.greenthumb.security.controller;

import com.greenthumb.security.JwtUtil;
import com.greenthumb.security.model.dto.JwtResponse;
import com.greenthumb.security.model.dto.UserCreateDTO;
import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {


    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserDTO registeredUser = userService.registerUser(userCreateDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(Authentication authentication) {
        String token = jwtUtil.createToken(authentication);
        JwtResponse jwtResponse = jwtUtil.buildJwtResponse(token, authentication);
        return ResponseEntity.ok(jwtResponse);
    }

}
