package com.greenthumb.security.service;

import com.greenthumb.security.model.dto.UserCreateDTO;
import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.model.entity.UserEntity;
import com.greenthumb.security.model.mapper.UserEntityMapper;
import com.greenthumb.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder){
        this.userRepo=userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    public UserDTO registerUser(UserCreateDTO userCreateDTO){
        UserEntity userEntity = UserEntityMapper.INSTANCE.toUserEntity(userCreateDTO);
        userEntity.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return UserEntityMapper.INSTANCE.toUserDTO(savedUserEntity);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return Collections.singletonList(UserEntityMapper.INSTANCE.toUserDTO(userRepo.findAllWithDetails()));

    }

}

