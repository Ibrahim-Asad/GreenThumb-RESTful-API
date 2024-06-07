package com.greenthumb.service.impl;

import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.model.entity.VolunteerActivity;
import com.greenthumb.model.mapper.VolunteerActivityMapper;
import com.greenthumb.repository.VolunteerActivityRepo;
import com.greenthumb.service.VolunteerActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerActivityServiceImpl implements VolunteerActivityService {

    @Autowired
    private VolunteerActivityRepo volunteerActivityRepo;

    @Autowired
    private VolunteerActivityMapper volunteerActivityMapper;

    @Override
    public List<VolunteerActivityDTO> getAllVolunteerActivities() {
        List<VolunteerActivity> volunteerActivities = volunteerActivityRepo.findAll();
        return volunteerActivities.stream()
                .map(volunteerActivityMapper::toVolunteerActivityDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerActivityDTO getVolunteerActivityById(Long id) {
        return volunteerActivityRepo.findById(id)
                .map(volunteerActivityMapper::toVolunteerActivityDTO)
                .orElse(null);
    }

    @Override
    public VolunteerActivityDTO createVolunteerActivity(VolunteerActivityDTO volunteerActivityDTO) {
        VolunteerActivity volunteerActivity = volunteerActivityMapper.toVolunteerActivity(volunteerActivityDTO);
        volunteerActivity = volunteerActivityRepo.save(volunteerActivity);
        return volunteerActivityMapper.toVolunteerActivityDTO(volunteerActivity);
    }

    @Override
    public VolunteerActivityDTO updateVolunteerActivity(Long id, VolunteerActivityDTO volunteerActivityDTO) {
        return volunteerActivityRepo.findById(id)
                .map(existingVolunteerActivity -> {
                    VolunteerActivity updatedVolunteerActivity = volunteerActivityMapper.toVolunteerActivity(volunteerActivityDTO);
                    updatedVolunteerActivity.setId(existingVolunteerActivity.getId());
                    updatedVolunteerActivity = volunteerActivityRepo.save(updatedVolunteerActivity);
                    return volunteerActivityMapper.toVolunteerActivityDTO(updatedVolunteerActivity);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteVolunteerActivity(Long id) {
        return volunteerActivityRepo.findById(id)
                .map(volunteerActivity -> {
                    volunteerActivityRepo.delete(volunteerActivity);
                    return true;
                })
                .orElse(false);
    }
}
