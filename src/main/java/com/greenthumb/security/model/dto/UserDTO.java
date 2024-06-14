package com.greenthumb.security.model.dto;

import com.greenthumb.model.dto.CommunityGardenDTos;
import com.greenthumb.model.dto.VolunteerActivityDTos;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private List<RoleDTO> roleEntities;
    private List<CommunityGardenDTos> communityGardens;
    private VolunteerActivityDTos volunteerActivity;

}
