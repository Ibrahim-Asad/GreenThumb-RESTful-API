package com.greenthumb.security.model.dto;

import com.greenthumb.security.model.entity.RoleEntity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private String username;
    private String email;
    private String password;
    private List<RoleEntity> roleEntities;
}
