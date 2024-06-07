package com.greenthumb.service.impl;



import com.greenthumb.model.dto.CropPlanDTO;
import com.greenthumb.model.entity.CropPlan;
import com.greenthumb.model.mapper.CropPlanMapper;
import com.greenthumb.repository.CropPlanRepo;
import com.greenthumb.service.CropPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CropPlanServiceImpl implements CropPlanService {

    private final CropPlanRepo cropPlanRepo;

    @Autowired
    public CropPlanServiceImpl(CropPlanRepo cropPlanRepo) {
        this.cropPlanRepo = cropPlanRepo;
    }

    @Override
    public List<CropPlanDTO> findAll() {
        return cropPlanRepo.findAll().stream()
                .map(CropPlanMapper.INSTANCE::tocropPlanDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CropPlanDTO findById(Long id) {
        CropPlan cropPlan = cropPlanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop plan not found"));
        return CropPlanMapper.INSTANCE.tocropPlanDTO(cropPlan);
    }

    @Override
    public CropPlanDTO create(CropPlanDTO cropPlanDTO) {
        CropPlan cropPlan = CropPlanMapper.INSTANCE.tocropPlan(cropPlanDTO);
        cropPlan = cropPlanRepo.save(cropPlan);
        return CropPlanMapper.INSTANCE.tocropPlanDTO(cropPlan);
    }

    @Override
    public CropPlanDTO update(Long id, CropPlanDTO cropPlanDTO) {
        CropPlan existingCropPlan = cropPlanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop plan not found"));
        existingCropPlan.setCropName(cropPlanDTO.getCropName());
        existingCropPlan.setPlantingDate(cropPlanDTO.getPlantingDate());
        existingCropPlan.setHarvestDate(cropPlanDTO.getHarvestDate());
        existingCropPlan = cropPlanRepo.save(existingCropPlan);
        return CropPlanMapper.INSTANCE.tocropPlanDTO(existingCropPlan);
    }

    @Override
    public void delete(Long id) {
        CropPlan cropPlan = cropPlanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop plan not found"));
        cropPlanRepo.delete(cropPlan);
    }
}

