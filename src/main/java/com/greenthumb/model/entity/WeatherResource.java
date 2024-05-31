package com.greenthumb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "weather_data")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String weatherCondition;
    private double temperature;

    @ManyToOne
    @JoinColumn(name = "community_garden_id")
    private CommunityGarden communityGarden;

    // Getters and Setters
}
