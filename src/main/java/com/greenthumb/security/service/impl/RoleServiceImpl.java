package com.greenthumb.security.service.impl;

import com.greenthumb.security.model.dto.RoleDTO;
import com.greenthumb.security.model.entity.RoleEntity;
import com.greenthumb.security.model.entity.UserEntity;
import com.greenthumb.security.model.mapper.RoleEntityMapper;
import com.greenthumb.security.repository.RoleRepo;
import com.greenthumb.security.repository.UserRepo;
import com.greenthumb.security.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepository;
    private final UserRepo userRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepository, UserRepo userRepository, RoleEntityMapper roleEntityMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleEntityMapper = roleEntityMapper;
    }

    @Override
    @Transactional
    public RoleDTO createRole(RoleDTO roleDTO) {
        RoleEntity roleEntity = roleEntityMapper.toRoleEntity(roleDTO);
        RoleEntity savedRoleEntity = roleRepository.save(roleEntity);
        return roleEntityMapper.toRoleDTO(savedRoleEntity);
    }

    @Override
    @Transactional
    public RoleDTO getRoleById(Long id) {
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        return roleEntity.map(roleEntityMapper::toRoleDTO).orElse(null);
    }

    @Override
    @Transactional
    public List<RoleDTO> getAllRoles() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream().map(roleEntityMapper::toRoleDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(id);
        if (optionalRoleEntity.isPresent()) {
            RoleEntity roleEntity = optionalRoleEntity.get();
            roleEntity.setName(roleDTO.getName());
            RoleEntity updatedRoleEntity = roleRepository.save(roleEntity);
            return roleEntityMapper.toRoleDTO(updatedRoleEntity);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addRoleToUser(Long roleId, Long userId) {
        Optional<RoleEntity> roleEntityOptional = roleRepository.findById(roleId);
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (roleEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            RoleEntity roleEntity = roleEntityOptional.get();
            UserEntity userEntity = userEntityOptional.get();

            if (!userEntity.getRoleEntities().contains(roleEntity)) {
                userEntity.getRoleEntities().add(roleEntity);
                roleEntity.getUsers().add(userEntity);
                userRepository.save(userEntity);
                roleRepository.save(roleEntity);
            }
        }
    }
}
