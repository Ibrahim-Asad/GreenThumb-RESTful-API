package com.greenthumb.controller;


import com.greenthumb.model.dto.VolunteerDTO;
import com.greenthumb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping
    public List<VolunteerDTO> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerById(@PathVariable Long id) {
        VolunteerDTO volunteerDTO = volunteerService.getVolunteerById(id);
        return volunteerDTO != null ? ResponseEntity.ok(volunteerDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public VolunteerDTO createVolunteer(@RequestBody VolunteerDTO volunteerDTO) {
        return volunteerService.createVolunteer(volunteerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable Long id, @RequestBody VolunteerDTO volunteerDTO) {
        VolunteerDTO updatedVolunteer = volunteerService.updateVolunteer(id, volunteerDTO);
        return updatedVolunteer != null ? ResponseEntity.ok(updatedVolunteer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        boolean isDeleted = volunteerService.deleteVolunteer(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
