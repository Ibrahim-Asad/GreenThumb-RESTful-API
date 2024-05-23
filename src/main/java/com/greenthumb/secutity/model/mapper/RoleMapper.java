package com.greenthumb.secutity.model.mapper;

import com.greenthumb.secutity.model.dto.RoleDTO;
import com.greenthumb.secutity.model.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO toDTO(RoleEntity role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public RoleEntity toEntity(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, RoleEntity.class);
    }
}
