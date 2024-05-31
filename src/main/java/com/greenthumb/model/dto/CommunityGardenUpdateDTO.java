package com.greenthumb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityGardenUpdateDTO {
    private String name;
    private String sunlight;
    private String soilType;
}
