package com.greenthumb.secutity.model.mapper;

import com.greenthumb.secutity.model.dto.RoleDTO;
import com.greenthumb.secutity.model.dto.UserDTO;
import com.greenthumb.secutity.model.entity.RoleEntity;
import com.greenthumb.secutity.model.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toDTO(UserEntity user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setRoles(user.getRoleEntities().stream()
                .map(roleEntity -> modelMapper.map(roleEntity, RoleDTO.class))
                .collect(Collectors.toList()));
        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        user.setRoleEntities(userDTO.getRoles().stream()
                .map(roleDTO -> modelMapper.map(roleDTO, RoleEntity.class))
                .collect(Collectors.toList()));
        return user;
    }
}
