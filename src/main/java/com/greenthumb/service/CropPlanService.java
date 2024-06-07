package com.greenthumb.service;



import com.greenthumb.model.dto.CropPlanDTO;

import java.util.List;

public interface CropPlanService {
    List<CropPlanDTO> findAll();
    CropPlanDTO findById(Long id);
    CropPlanDTO create(CropPlanDTO cropPlanDTO);
    CropPlanDTO update(Long id, CropPlanDTO cropPlanDTO);
    void delete(Long id);
}

