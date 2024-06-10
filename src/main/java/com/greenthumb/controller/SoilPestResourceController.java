package com.greenthumb.controller;

import com.greenthumb.model.dto.SoilPestResourceDTO;
import com.greenthumb.service.SoilPestResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SoilPestResourceController {

    private SoilPestResourceService soilPestResourceService;

    @Autowired
    public SoilPestResourceController(SoilPestResourceService soilPestResourceService) {
        this.soilPestResourceService = soilPestResourceService;
    }

    @GetMapping("/volunteer/soil-pest-resources/{id}")
    public ResponseEntity<SoilPestResourceDTO> getSoilPestResourceById(@PathVariable Long id) {
        SoilPestResourceDTO soilPestResourceDTO = soilPestResourceService.getSoilPestResourceById(id);
        return ResponseEntity.ok(soilPestResourceDTO);
    }

    @GetMapping("/volunteer/all-soil-pest-resources")
    public ResponseEntity<List<SoilPestResourceDTO>> getAllSoilPestResources() {
        List<SoilPestResourceDTO> soilPestResources = soilPestResourceService.getAllSoilPestResources();
        return ResponseEntity.ok(soilPestResources);
    }

    @PostMapping("/user/soil-pest-resources")
    public ResponseEntity<SoilPestResourceDTO> createSoilPestResource(@RequestBody SoilPestResourceDTO soilPestResourceDTO) {
        SoilPestResourceDTO createdResource = soilPestResourceService.createSoilPestResource(soilPestResourceDTO);
        return ResponseEntity.ok(createdResource);
    }

    @PutMapping("/user/soil-pest-resources/{id}")
    public ResponseEntity<SoilPestResourceDTO> updateSoilPestResource(@PathVariable Long id, @RequestBody SoilPestResourceDTO soilPestResourceDTO) {
        SoilPestResourceDTO updatedResource = soilPestResourceService.updateSoilPestResource(id, soilPestResourceDTO);
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/user/soil-pest-resources/{id}")
    public ResponseEntity<Void> deleteSoilPestResource(@PathVariable Long id) {
        soilPestResourceService.deleteSoilPestResource(id);
        return ResponseEntity.noContent().build();
    }
}
