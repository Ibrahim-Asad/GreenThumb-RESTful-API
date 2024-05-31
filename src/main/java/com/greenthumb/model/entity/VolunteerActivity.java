package com.greenthumb.model.entity;

import com.greenthumb.security.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "volunteer_activity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityName;
    private Date date;
    private String description;


    @ManyToMany(mappedBy = "volunteerActivities")
    private List<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "volunteer_activity_volunteer",
            joinColumns = @JoinColumn(name = "volunteer_activity_id"),
            inverseJoinColumns = @JoinColumn(name = "volunteer_id")
    )
    private List<Volunteer> volunteers;
}
