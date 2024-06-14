package com.greenthumb.service;

import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.model.dto.VolunteerDTO;

import java.util.List;

public interface VolunteerService {
    List<VolunteerDTO> getAllVolunteers();
    VolunteerDTO getVolunteerById(Long id);
    VolunteerDTO createVolunteer(VolunteerDTO volunteerDTO);
    VolunteerDTO updateVolunteer(Long id, VolunteerDTO volunteerDTO);
    boolean deleteVolunteer(Long id);
    VolunteerDTO scheduleActivityForVolunteer(Long volunteerId, Long activityId);
    List<VolunteerActivityDTO> getActivitiesForVolunteer(Long volunteerId);
}

