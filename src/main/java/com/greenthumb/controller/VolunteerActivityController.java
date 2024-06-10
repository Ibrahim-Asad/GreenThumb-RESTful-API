package com.greenthumb.controller;

import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.service.VolunteerActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VolunteerActivityController {

    @Autowired
    private VolunteerActivityService volunteerActivityService;

    @GetMapping("/volunteer/volunteer-activities")
    public List<VolunteerActivityDTO> getAllVolunteerActivities() {
        return volunteerActivityService.getAllVolunteerActivities();
    }

    @GetMapping("/volunteer/{id}/volunteer-activities")
    public ResponseEntity<VolunteerActivityDTO> getVolunteerActivityById(@PathVariable Long id) {
        VolunteerActivityDTO volunteerActivityDTO = volunteerActivityService.getVolunteerActivityById(id);
        return volunteerActivityDTO != null ? ResponseEntity.ok(volunteerActivityDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/volunteer-activities")
    public VolunteerActivityDTO createVolunteerActivity(@RequestBody VolunteerActivityDTO volunteerActivityDTO) {
        return volunteerActivityService.createVolunteerActivity(volunteerActivityDTO);
    }

    @PutMapping("/admin/{id}/volunteer-activities")
    public ResponseEntity<VolunteerActivityDTO> updateVolunteerActivity(@PathVariable Long id, @RequestBody VolunteerActivityDTO volunteerActivityDTO) {
        VolunteerActivityDTO updatedVolunteerActivity = volunteerActivityService.updateVolunteerActivity(id, volunteerActivityDTO);
        return updatedVolunteerActivity != null ? ResponseEntity.ok(updatedVolunteerActivity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/admin/{id}/volunteer-activities")
    public ResponseEntity<Void> deleteVolunteerActivity(@PathVariable Long id) {
        boolean isDeleted = volunteerActivityService.deleteVolunteerActivity(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
