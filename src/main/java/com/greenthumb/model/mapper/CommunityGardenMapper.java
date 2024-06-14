package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.model.dto.CommunityGardenDTos;
import com.greenthumb.security.model.dto.UserDTO;
import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.security.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommunityGardenMapper {

    CommunityGardenMapper INSTANCE = Mappers.getMapper(CommunityGardenMapper.class);

    CommunityGardenDTO toCommunityGardenDTO(CommunityGarden communityGarden);

    CommunityGarden toCommunityGarden(CommunityGardenDTO communityGardenDTO);

    CommunityGardenDTos toCommunityGardenDTos(CommunityGarden communityGarden);
    CommunityGarden toCommunityGarden(CommunityGardenDTos communityGardenDTO);


    default List<UserDTO> mapUsers(List<UserEntity> users) {
        return users.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .build())
                .collect(Collectors.toList());
    }

    default List<UserEntity> mapUserDTOs(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(userDTO -> UserEntity.builder()
                        .id(userDTO.getId())
                        .username(userDTO.getUsername())
                        .build())
                .collect(Collectors.toList());
    }
}
