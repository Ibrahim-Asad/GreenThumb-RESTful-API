package com.greenthumb.security.service;

import com.greenthumb.security.model.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO getRoleById(Long id);

    List<RoleDTO> getAllRoles();

    RoleDTO updateRole(Long id, RoleDTO roleDTO);

    void deleteRole(Long id);

    void addRoleToUser(Long roleId, Long userId);
}
