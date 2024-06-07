package com.greenthumb.service.impl;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.repository.CommunityGardenRepo;
import com.greenthumb.model.mapper.CommunityGardenMapper;
import com.greenthumb.service.CommunityGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityGardenServiceImpl implements CommunityGardenService {

    private final CommunityGardenRepo communityGardenRepo;

    @Autowired
    public CommunityGardenServiceImpl(CommunityGardenRepo communityGardenRepo) {
        this.communityGardenRepo = communityGardenRepo;
    }

    @Override
    public List<CommunityGardenDTO> findAll() {
        return communityGardenRepo.findAll()
                .stream()
                .map(CommunityGardenMapper.INSTANCE::tocommunityGardenDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommunityGardenDTO findById(Long id) {
        return communityGardenRepo.findById(id)
                .map(CommunityGardenMapper.INSTANCE::tocommunityGardenDTO)
                .orElseThrow(() -> new RuntimeException("Community Garden not found"));
    }

    @Override
    public CommunityGardenDTO create(CommunityGardenDTO communityGardenDTO) {
        CommunityGarden garden = CommunityGardenMapper.INSTANCE.tocommunityGarden(communityGardenDTO);
        garden = communityGardenRepo.save(garden);
        return CommunityGardenMapper.INSTANCE.tocommunityGardenDTO(garden);
    }

    @Override
    public CommunityGardenDTO update(Long id, CommunityGardenDTO communityGardenDTO) {
        CommunityGarden existingGarden = communityGardenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Community Garden not found"));
        existingGarden.setName(communityGardenDTO.getName());
        existingGarden.setLocation(communityGardenDTO.getLocation());
        existingGarden.setSunlight(communityGardenDTO.getSunlight());
        existingGarden.setSoilType(communityGardenDTO.getSoilType());
        existingGarden = communityGardenRepo.save(existingGarden);
        return CommunityGardenMapper.INSTANCE.tocommunityGardenDTO(existingGarden);
    }

    @Override
    public void delete(Long id) {
        CommunityGarden garden = communityGardenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Community Garden not found"));
        communityGardenRepo.delete(garden);
    }
}