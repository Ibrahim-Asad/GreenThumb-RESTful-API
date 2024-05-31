package com.greenthumb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDTO {
    private String name;
    private String contactInfo;
    private String skills;
    private String availability;
}
