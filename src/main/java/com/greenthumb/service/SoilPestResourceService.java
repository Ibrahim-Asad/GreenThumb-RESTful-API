package com.greenthumb.service;

import com.greenthumb.model.dto.SoilPestResourceDTO;

import java.util.List;

public interface SoilPestResourceService {
    SoilPestResourceDTO createSoilPestResource(SoilPestResourceDTO soilPestResourceDTO);
    SoilPestResourceDTO getSoilPestResourceById(Long id);
    List<SoilPestResourceDTO> getAllSoilPestResources();
    SoilPestResourceDTO updateSoilPestResource(Long id, SoilPestResourceDTO soilPestResourceDTO);
    void deleteSoilPestResource(Long id);
}
