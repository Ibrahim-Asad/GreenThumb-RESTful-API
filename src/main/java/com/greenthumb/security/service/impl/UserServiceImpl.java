package com.greenthumb.security.service.impl;

import com.greenthumb.security.model.dto.UserCreateDTO;
import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.model.dto.UserUpdateDTO;
import com.greenthumb.security.model.entity.UserEntity;
import com.greenthumb.security.model.mapper.UserEntityMapper;
import com.greenthumb.security.repository.UserRepo;
import com.greenthumb.security.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepo userRepo,PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
        this.userRepo=userRepo;
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = (List<UserEntity>) userRepo.findAllWithDetails();
        return users.stream()
                .map(UserEntityMapper.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepo.findById(id);
        if (userEntityOptional.isPresent()) {
            return UserEntityMapper.INSTANCE.toUserDTO(userEntityOptional.get());
        } else {
            throw new UsernameNotFoundException("User with id " + id + " not found");
        }
    }


    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = UserEntityMapper.INSTANCE.toUserEntity(userCreateDTO);
        userEntity.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return UserEntityMapper.INSTANCE.toUserDTO(savedUserEntity);
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        // Update the fields of the existing user entity
        userEntity.setUsername(userUpdateDTO.getUsername());
        userEntity.setEmail(userUpdateDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        userEntity.setRoleEntities(userUpdateDTO.getRoleEntities());

        // Save the updated user entity back to the repository
        userEntity = userRepo.save(userEntity);

        return UserEntityMapper.INSTANCE.toUserDTO(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepo.deleteById(id);
    }


}

