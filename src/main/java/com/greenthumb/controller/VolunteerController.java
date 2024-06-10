package com.greenthumb.controller;


import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.model.dto.VolunteerDTO;
import com.greenthumb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/volunteer/volunteers")
    public List<VolunteerDTO> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @GetMapping("/volunteer/{id}/volunteers")
    public ResponseEntity<VolunteerDTO> getVolunteerById(@PathVariable Long id) {
        VolunteerDTO volunteerDTO = volunteerService.getVolunteerById(id);
        return volunteerDTO != null ? ResponseEntity.ok(volunteerDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/volunteers")
    public VolunteerDTO createVolunteer(@RequestBody VolunteerDTO volunteerDTO) {
        return volunteerService.createVolunteer(volunteerDTO);
    }

    @PutMapping("/admin/{id}/volunteers")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable Long id, @RequestBody VolunteerDTO volunteerDTO) {
        VolunteerDTO updatedVolunteer = volunteerService.updateVolunteer(id, volunteerDTO);
        return updatedVolunteer != null ? ResponseEntity.ok(updatedVolunteer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/admin/{id}/volunteers")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        boolean isDeleted = volunteerService.deleteVolunteer(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/volunteer/{id}/schedule/{activityId}/volunteers")
    public ResponseEntity<VolunteerDTO> scheduleActivityForVolunteer(@PathVariable Long id, @PathVariable Long activityId) {
        VolunteerDTO volunteerDTO = volunteerService.scheduleActivityForVolunteer(id, activityId);
        return volunteerDTO != null ? ResponseEntity.ok(volunteerDTO) : ResponseEntity.notFound().build();
    }
    @GetMapping("/volunteer/{id}/activities/volunteers")
    public ResponseEntity<List<VolunteerActivityDTO>> getActivitiesForVolunteer(@PathVariable Long id) {
        List<VolunteerActivityDTO> activities = volunteerService.getActivitiesForVolunteer(id);
        return activities != null ? ResponseEntity.ok(activities) : ResponseEntity.notFound().build();
    }
}
