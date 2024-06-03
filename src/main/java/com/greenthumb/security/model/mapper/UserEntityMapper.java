package com.greenthumb.security.model.mapper;

import com.greenthumb.security.model.dto.UserCreateDTO;
import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.security.model.dto.UserUpdateDTO;
import com.greenthumb.security.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    UserDTO toUserDTO(UserEntity userEntity);
    UserUpdateDTO toUserUpdateDTO(UserEntity userEntity);
    UserCreateDTO toUserCreateDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserDTO userDTO);
    UserEntity toUserEntity(UserUpdateDTO userUpdateDTO);
    UserEntity toUserEntity(UserCreateDTO userCreateDTO);
    List<UserDTO> toUserDTOs(List<UserEntity> userEntities);

    default UserDetails toUserDetails(UserEntity userEntity) {
        Collection<GrantedAuthority> authorities = userEntity.getRoleEntities().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
