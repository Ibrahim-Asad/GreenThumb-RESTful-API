package com.greenthumb.security.model.dto;

import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.model.entity.VolunteerActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private List<RoleDTO> roleEntities;
    private List<CommunityGarden> communityGardens;
    private List<VolunteerActivity> volunteerActivities;
}
