package com.greenthumb.model.dto;

import com.greenthumb.model.entity.CommunityGarden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResourceDTO {
    private Date date;
    private String weatherCondition;
    private double temperature;
    private CommunityGarden communityGarden;
}
