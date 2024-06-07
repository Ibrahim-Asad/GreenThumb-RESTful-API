package com.greenthumb.controller;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.service.CommunityGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communityGardens")
public class CommunityGardenController {

    private final CommunityGardenService communityGardenService;

    @Autowired
    public CommunityGardenController(CommunityGardenService communityGardenService) {
        this.communityGardenService = communityGardenService;
    }

    @GetMapping
    public ResponseEntity<List<CommunityGardenDTO>> getAllCommunityGardens() {
        List<CommunityGardenDTO> gardens = communityGardenService.findAll();
        return ResponseEntity.ok(gardens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityGardenDTO> getCommunityGardenById(@PathVariable Long id) {
        return ResponseEntity.ok(communityGardenService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CommunityGardenDTO> createCommunityGarden(@RequestBody CommunityGardenDTO communityGardenDTO) {
        CommunityGardenDTO created = communityGardenService.create(communityGardenDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityGardenDTO> updateCommunityGarden(@PathVariable Long id, @RequestBody CommunityGardenDTO communityGardenDTO) {
        CommunityGardenDTO updated = communityGardenService.update(id, communityGardenDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunityGarden(@PathVariable Long id) {
        communityGardenService.delete(id);
        return ResponseEntity.ok().build();
    }
}
