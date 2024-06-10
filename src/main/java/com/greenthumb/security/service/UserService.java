package com.greenthumb.security.service;

import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.model.dto.UserCreateDTO;
import com.greenthumb.security.model.dto.UserUpdateDTO;
import com.greenthumb.security.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserCreateDTO userCreateDTO);
    UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
    void deleteUser(Long id);
}
