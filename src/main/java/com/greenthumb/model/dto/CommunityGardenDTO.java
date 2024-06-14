package com.greenthumb.model.dto;

import com.greenthumb.security.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityGardenDTO {
    private String name;
    private String location;
    private String sunlight;
    private String soilType;
    private List<UserDTO> users;
}
