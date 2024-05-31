package com.greenthumb.security.model.entity;

import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.model.entity.VolunteerActivity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters long")
    private String username;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roleEntities;

    @ManyToMany
    @JoinTable(
            name = "user_community_garden",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "community_garden_id")
    )
    private List<CommunityGarden> communityGardens;

    @ManyToMany
    @JoinTable(
            name = "user_volunteer_activity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "volunteer_activity_id")
    )
    private List<VolunteerActivity> volunteerActivities;
}
