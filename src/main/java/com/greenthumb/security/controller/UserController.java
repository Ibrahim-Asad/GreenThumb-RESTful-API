package com.greenthumb.security.controller;


import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/get-users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

}
