package com.greenthumb.controller;



import com.greenthumb.model.dto.CropPlanDTO;
import com.greenthumb.service.CropPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CropPlanController {

    private final CropPlanService cropPlanService;

    @Autowired
    public CropPlanController(CropPlanService cropPlanService) {
        this.cropPlanService = cropPlanService;
    }

    @GetMapping
    public ResponseEntity<List<CropPlanDTO>> getAllCropPlans() {
        return ResponseEntity.ok(cropPlanService.findAll());
    }

    @GetMapping("/user/{id}/cropPlans")
    public ResponseEntity<CropPlanDTO> getCropPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(cropPlanService.findById(id));
    }

    @PostMapping("/admin/cropPlans")
    public ResponseEntity<CropPlanDTO> createCropPlan(@RequestBody CropPlanDTO cropPlanDTO) {
        CropPlanDTO newCropPlan = cropPlanService.create(cropPlanDTO);
        return ResponseEntity.ok(newCropPlan);
    }

    @PutMapping("/admin/{id}/cropPlans")
    public ResponseEntity<CropPlanDTO> updateCropPlan(@PathVariable Long id, @RequestBody CropPlanDTO cropPlanDTO) {
        CropPlanDTO updatedCropPlan = cropPlanService.update(id, cropPlanDTO);
        return ResponseEntity.ok(updatedCropPlan);
    }

    @DeleteMapping("/admin/{id}/cropPlans")
    public ResponseEntity<Void> deleteCropPlan(@PathVariable Long id) {
        cropPlanService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/schedule/cropPlans")
    public ResponseEntity<CropPlanDTO> scheduleCropPlan(
            @RequestParam Long userId, @RequestParam Long plotId, @RequestBody CropPlanDTO cropPlanDTO
    )
    {
        CropPlanDTO scheduledCropPlan = cropPlanService.scheduleCropPlan(userId, plotId, cropPlanDTO);
        return ResponseEntity.ok(scheduledCropPlan);
    }
}

