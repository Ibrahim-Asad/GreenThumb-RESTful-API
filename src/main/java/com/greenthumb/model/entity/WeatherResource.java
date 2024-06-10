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

    private double latitude;
    private double longitude;
    private String cityName;
    private String country;
    private double temperature;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private int windDegree;
    private int cloudiness;
    private Date date;
    private Date sunrise;
    private Date sunset;

    @ManyToOne
    @JoinColumn(name = "community_garden_id")
    private CommunityGarden communityGarden;
}
