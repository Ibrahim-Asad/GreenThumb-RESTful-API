package com.greenthumb.controller;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.service.CommunityGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommunityGardenController {

    private final CommunityGardenService communityGardenService;

    @Autowired
    public CommunityGardenController(CommunityGardenService communityGardenService) {
        this.communityGardenService = communityGardenService;
    }

    @GetMapping("/volunteer/communityGardens")
    public ResponseEntity<List<CommunityGardenDTO>> getAllCommunityGardens() {
        List<CommunityGardenDTO> gardens = communityGardenService.findAll();
        return ResponseEntity.ok(gardens);
    }

    @GetMapping("/user/communityGardens/{id}")
    public ResponseEntity<CommunityGardenDTO> getCommunityGardenById(@PathVariable Long id) {
        return ResponseEntity.ok(communityGardenService.findById(id));
    }

    @PostMapping("/admin/communityGardens")
    public ResponseEntity<CommunityGardenDTO> createCommunityGarden(@RequestBody CommunityGardenDTO communityGardenDTO) {
        CommunityGardenDTO created = communityGardenService.create(communityGardenDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/admin/{id}/communityGardens")
    public ResponseEntity<CommunityGardenDTO> updateCommunityGarden(@PathVariable Long id, @RequestBody CommunityGardenDTO communityGardenDTO) {
        CommunityGardenDTO updated = communityGardenService.update(id, communityGardenDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/{id}/communityGardens")
    public ResponseEntity<Void> deleteCommunityGarden(@PathVariable Long id) {
        communityGardenService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/{gardenId}/users/{userId}/communityGardens")
    public ResponseEntity<Void> addUserToCommunityGarden(
            @PathVariable Long gardenId,
            @PathVariable Long userId
    ) {
        communityGardenService.addUserToCommunityGarden(gardenId, userId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/admin/{gardenId}/users/communityGardens")
    public ResponseEntity<CommunityGardenDTO> getCommunityGardenWithUsers(@PathVariable Long gardenId) {
        return ResponseEntity.ok(communityGardenService.getCommunityGardenWithUsers(gardenId));
    }
}
