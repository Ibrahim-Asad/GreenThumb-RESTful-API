package com.greenthumb.service.impl;

import com.greenthumb.model.dto.SoilPestResourceDTO;
import com.greenthumb.model.entity.SoilPestResource;
import com.greenthumb.model.mapper.SoilPestResourceMapper;
import com.greenthumb.repository.SoilPestResourceRepo;
import com.greenthumb.service.SoilPestResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoilPestResourceServiceImpl implements SoilPestResourceService {


    private SoilPestResourceRepo soilPestResourceRepository;
    private SoilPestResourceMapper soilPestResourceMapper;

    @Autowired
    public SoilPestResourceServiceImpl(SoilPestResourceRepo soilPestResourceRepository, SoilPestResourceMapper soilPestResourceMapper) {
        this.soilPestResourceRepository = soilPestResourceRepository;
        this.soilPestResourceMapper = soilPestResourceMapper;
    }

    @Override
    public SoilPestResourceDTO createSoilPestResource(SoilPestResourceDTO soilPestResourceDTO) {
        SoilPestResource soilPestResource = soilPestResourceMapper.toSoilPestResource(soilPestResourceDTO);
        soilPestResource = soilPestResourceRepository.save(soilPestResource);
        return soilPestResourceMapper.toSoilPestResourceDTO(soilPestResource);
    }

    @Override
    public SoilPestResourceDTO getSoilPestResourceById(Long id) {
        SoilPestResource soilPestResource = soilPestResourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        return soilPestResourceMapper.toSoilPestResourceDTO(soilPestResource);
    }

    @Override
    public List<SoilPestResourceDTO> getAllSoilPestResources() {
        return soilPestResourceRepository.findAll().stream()
                .map(soilPestResourceMapper::toSoilPestResourceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SoilPestResourceDTO updateSoilPestResource(Long id, SoilPestResourceDTO soilPestResourceDTO) {
        SoilPestResource soilPestResource = soilPestResourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        soilPestResource.setResourceType(soilPestResourceDTO.getResourceType());
        soilPestResource.setDescription(soilPestResourceDTO.getDescription());
        soilPestResource = soilPestResourceRepository.save(soilPestResource);
        return soilPestResourceMapper.toSoilPestResourceDTO(soilPestResource);
    }

    @Override
    public void deleteSoilPestResource(Long id) {
        soilPestResourceRepository.deleteById(id);
    }
}
