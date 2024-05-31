package com.greenthumb.security.model.mapper;

import com.greenthumb.security.model.dto.RoleDTO;
import com.greenthumb.security.model.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleEntityMapper {

    RoleEntityMapper INSTANCE = Mappers.getMapper(RoleEntityMapper.class);

    RoleEntity toRoleEntity(RoleDTO roleDTO);
    RoleDTO toRoleDTO(RoleEntity roleEntity);
}
