package com.greenthumb.security.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String username;
    private String email;
    private String password;
    private List<Long> roleIds;
}