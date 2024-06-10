package com.greenthumb.model.dto;

import com.greenthumb.model.entity.VolunteerActivity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDTO {
    private String name;
    private String contactInfo;
    private String skills;
    private String availability;
    private List<VolunteerActivityDTos> activities;
}
