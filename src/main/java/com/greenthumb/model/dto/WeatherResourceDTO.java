package com.greenthumb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResourceDTO {
    private Coord coord;
    private Main main;
    private String name;
    private Sys sys;
    private Wind wind;
    private Clouds clouds;
    private long dt;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Coord {
        private double lon;
        private double lat;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sys {
        private String country;
        private long sunrise;
        private long sunset;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Wind {
        private double speed;
        private int deg;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Clouds {
        private int all;
    }
}
