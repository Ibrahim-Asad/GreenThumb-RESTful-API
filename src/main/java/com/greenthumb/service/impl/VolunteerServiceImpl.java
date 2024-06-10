package com.greenthumb.service.impl;



import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.model.dto.VolunteerDTO;
import com.greenthumb.model.entity.Volunteer;
import com.greenthumb.model.entity.VolunteerActivity;
import com.greenthumb.model.mapper.VolunteerActivityMapper;
import com.greenthumb.model.mapper.VolunteerMapper;
import com.greenthumb.repository.VolunteerActivityRepo;
import com.greenthumb.repository.VolunteerRepo;
import com.greenthumb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private VolunteerRepo volunteerRepo;
    private VolunteerActivityRepo volunteerActivityRepo;
    private VolunteerMapper volunteerMapper;

    @Autowired
    public VolunteerServiceImpl(VolunteerRepo volunteerRepo, VolunteerActivityRepo volunteerActivityRepo, VolunteerMapper volunteerMapper) {
        this.volunteerRepo = volunteerRepo;
        this.volunteerActivityRepo = volunteerActivityRepo;
        this.volunteerMapper = volunteerMapper;
    }

    @Override
    public List<VolunteerDTO> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerRepo.findAll();
        return volunteers.stream()
                .map(volunteerMapper::toVolunteerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerDTO getVolunteerById(Long id) {
        return volunteerRepo.findById(id)
                .map(volunteerMapper::toVolunteerDTO)
                .orElse(null);
    }

    @Override
    public VolunteerDTO createVolunteer(VolunteerDTO volunteerDTO) {
        Volunteer volunteer = volunteerMapper.toVolunteer(volunteerDTO);
        volunteer = volunteerRepo.save(volunteer);
        return volunteerMapper.toVolunteerDTO(volunteer);
    }

    @Override
    public VolunteerDTO updateVolunteer(Long id, VolunteerDTO volunteerDTO) {
        return volunteerRepo.findById(id)
                .map(existingVolunteer -> {
                    Volunteer updatedVolunteer = volunteerMapper.toVolunteer(volunteerDTO);
                    updatedVolunteer.setId(existingVolunteer.getId());
                    updatedVolunteer = volunteerRepo.save(updatedVolunteer);
                    return volunteerMapper.toVolunteerDTO(updatedVolunteer);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteVolunteer(Long id) {
        return volunteerRepo.findById(id)
                .map(volunteer -> {
                    volunteerRepo.delete(volunteer);
                    return true;
                })
                .orElse(false);
    }

    @Override
    @Transactional
    public VolunteerDTO scheduleActivityForVolunteer(Long volunteerId, Long activityId) {
        Optional<Volunteer> volunteerOptional = volunteerRepo.findById(volunteerId);
        Optional<VolunteerActivity> activityOptional = volunteerActivityRepo.findById(activityId);

        if (volunteerOptional.isPresent() && activityOptional.isPresent()) {
            Volunteer volunteer = volunteerOptional.get();
            VolunteerActivity activity = activityOptional.get();

            if (!volunteer.getActivities().contains(activity)) {
                volunteer.getActivities().add(activity);
                activity.getVolunteers().add(volunteer);
                volunteerRepo.save(volunteer);
                volunteerActivityRepo.save(activity);
            }

            return volunteerMapper.INSTANCE.toVolunteerDTO(volunteer);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<VolunteerActivityDTO> getActivitiesForVolunteer(Long volunteerId) {
        Optional<Volunteer> volunteerOptional = volunteerRepo.findById(volunteerId);
        if (volunteerOptional.isPresent()) {
            Volunteer volunteer = volunteerOptional.get();
            return volunteer.getActivities().stream()
                    .map(VolunteerActivityMapper.INSTANCE::toVolunteerActivityDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

}

