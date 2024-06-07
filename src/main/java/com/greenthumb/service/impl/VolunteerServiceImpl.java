package com.greenthumb.service.impl;



import com.greenthumb.model.dto.VolunteerDTO;
import com.greenthumb.model.entity.Volunteer;
import com.greenthumb.model.mapper.VolunteerMapper;
import com.greenthumb.repository.VolunteerRepo;
import com.greenthumb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerRepo volunteerRepo;

    @Autowired
    private VolunteerMapper volunteerMapper;

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
}

