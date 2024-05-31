package com.greenthumb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plot")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plotName;
    private String sunlight;
    private String soilType;

    @ManyToOne
    @JoinColumn(name = "community_garden_id")
    private CommunityGarden communityGarden;
}
