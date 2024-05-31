package com.greenthumb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "soil_pest_data")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoilPestResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceType;
    private String description;

    @ManyToOne
    @JoinColumn(name = "community_garden_id")
    private CommunityGarden communityGarden;

}
