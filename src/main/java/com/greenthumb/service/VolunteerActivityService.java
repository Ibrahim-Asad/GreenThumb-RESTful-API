package com.greenthumb.service;


import com.greenthumb.model.dto.VolunteerActivityDTO;

import java.util.List;

public interface VolunteerActivityService {
    List<VolunteerActivityDTO> getAllVolunteerActivities();
    VolunteerActivityDTO getVolunteerActivityById(Long id);
    VolunteerActivityDTO createVolunteerActivity(VolunteerActivityDTO volunteerActivityDTO);
    VolunteerActivityDTO updateVolunteerActivity(Long id, VolunteerActivityDTO volunteerActivityDTO);
    boolean deleteVolunteerActivity(Long id);
}
