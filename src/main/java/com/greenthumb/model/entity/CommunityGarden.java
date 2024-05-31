package com.greenthumb.model.entity;

import com.greenthumb.security.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "community_garden")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityGarden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String sunlight;
    private String soilType;

    @ManyToMany(mappedBy = "communityGardens")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "communityGarden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plot> plots;
}
