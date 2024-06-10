package com.greenthumb.security.service.impl;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.model.dto.CommunityGardenDTos;
import com.greenthumb.model.dto.VolunteerActivityDTos;
import com.greenthumb.security.model.dto.RoleDTO;
import com.greenthumb.security.model.dto.UserCreateDTO;
import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.model.dto.UserUpdateDTO;
import com.greenthumb.security.model.entity.RoleEntity;
import com.greenthumb.security.model.entity.UserEntity;
import com.greenthumb.security.model.mapper.UserEntityMapper;
import com.greenthumb.security.repository.RoleRepo;
import com.greenthumb.security.repository.UserRepo;
import com.greenthumb.security.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.PasswordView;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;




    @Autowired
    public UserServiceImpl(UserRepo userRepo,PasswordEncoder passwordEncoder,RoleRepo roleRepo){
        this.passwordEncoder=passwordEncoder;
        this.userRepo=userRepo;
        this.roleRepo=roleRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(UserEntity user) {
        List<RoleDTO> roleDTOs = user.getRoleEntities().stream()
                .map(role -> new RoleDTO(role.getId(), role.getName()))
                .collect(Collectors.toList());

        List<CommunityGardenDTos> communityGardenDTOs = user.getCommunityGardens().stream()
                .map(garden -> new CommunityGardenDTos(garden.getId(),garden.getName()))
                .collect(Collectors.toList());

        VolunteerActivityDTos volunteerActivityDTO = null;
        if (user.getVolunteerActivity() != null) {
            volunteerActivityDTO = new VolunteerActivityDTos(user.getVolunteerActivity().getId(),user.getVolunteerActivity().getActivityName());
        }

        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), roleDTOs, communityGardenDTOs, volunteerActivityDTO);
    }


    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepo.findById(id);
        if (userEntityOptional.isPresent()) {
            return UserEntityMapper.INSTANCE.toUserDTO(userEntityOptional.get());
        } else {
            throw new UsernameNotFoundException("User with id " + id + " not found");
        }
    }

    @Transactional
    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = UserEntityMapper.INSTANCE.toUserEntity(userCreateDTO);

        List<RoleEntity> roleEntities = userCreateDTO.getRoleIds().stream()
                .map(roleId -> roleRepo.findById(roleId)
                        .orElseThrow(() -> new EntityNotFoundException("Role with id " + roleId + " not found")))
                .collect(Collectors.toList());
        userEntity.setRoleEntities(roleEntities);

        userEntity.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return UserEntityMapper.INSTANCE.toUserDTO(savedUserEntity);
    }


    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        userEntity.setUsername(userUpdateDTO.getUsername());
        userEntity.setEmail(userUpdateDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        userEntity.setRoleEntities(userUpdateDTO.getRoleEntities());

        userEntity = userRepo.save(userEntity);

        return UserEntityMapper.INSTANCE.toUserDTO(userEntity);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepo.deleteById(id);
    }



}

