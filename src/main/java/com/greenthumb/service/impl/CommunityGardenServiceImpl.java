package com.greenthumb.service.impl;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.repository.CommunityGardenRepo;
import com.greenthumb.model.mapper.CommunityGardenMapper;
import com.greenthumb.security.model.entity.UserEntity;
import com.greenthumb.security.repository.UserRepo;
import com.greenthumb.service.CommunityGardenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityGardenServiceImpl implements CommunityGardenService {

    private final CommunityGardenRepo communityGardenRepo;
    private final UserRepo userRepo;

    @Autowired
    public CommunityGardenServiceImpl(CommunityGardenRepo communityGardenRepo,UserRepo userRepo) {
        this.communityGardenRepo = communityGardenRepo;
        this.userRepo=userRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommunityGardenDTO> findAll() {
        return communityGardenRepo.findAll()
                .stream()
                .map(CommunityGardenMapper.INSTANCE::toCommunityGardenDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CommunityGardenDTO findById(Long id) {
        return communityGardenRepo.findById(id)
                .map(CommunityGardenMapper.INSTANCE::toCommunityGardenDTO)
                .orElseThrow(() -> new RuntimeException("Community Garden not found"));
    }

    @Transactional
    @Override
    public CommunityGardenDTO create(CommunityGardenDTO communityGardenDTO) {
        CommunityGarden garden = CommunityGardenMapper.INSTANCE.toCommunityGarden(communityGardenDTO);
        garden = communityGardenRepo.save(garden);
        return CommunityGardenMapper.INSTANCE.toCommunityGardenDTO(garden);
    }

    @Transactional
    @Override
    public CommunityGardenDTO update(Long id, CommunityGardenDTO communityGardenDTO) {
        CommunityGarden existingGarden = communityGardenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Community Garden not found"));
        existingGarden.setName(communityGardenDTO.getName());
        existingGarden.setLocation(communityGardenDTO.getLocation());
        existingGarden.setSunlight(communityGardenDTO.getSunlight());
        existingGarden.setSoilType(communityGardenDTO.getSoilType());
        existingGarden = communityGardenRepo.save(existingGarden);
        return CommunityGardenMapper.INSTANCE.toCommunityGardenDTO(existingGarden);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        CommunityGarden garden = communityGardenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Community Garden not found"));
        communityGardenRepo.delete(garden);
    }

    @Transactional
    @Override
    public void addUserToCommunityGarden(Long gardenId, Long userId) {
        Optional<CommunityGarden> communityGarden = communityGardenRepo.findById(gardenId);
        Optional<UserEntity> userEntityOptional = userRepo.findById(userId);
        if (communityGarden.isPresent() && userEntityOptional.isPresent()) {
            CommunityGarden garden = communityGarden.get();
            UserEntity userEntity = userEntityOptional.get();

            if (!userEntity.getRoleEntities().contains(garden)) {
                userEntity.getCommunityGardens().add(garden);
                garden.getUsers().add(userEntity);
                userRepo.save(userEntity);
                communityGardenRepo.save(garden);
            }
        }

    }

    @Transactional(readOnly = true)
    @Override
    public CommunityGardenDTO getCommunityGardenWithUsers(Long gardenId) {
        CommunityGarden communityGarden = communityGardenRepo.findById(gardenId)
                .orElseThrow(() -> new EntityNotFoundException("Community Garden not found"));

        communityGarden.getUsers().size();

        return CommunityGardenMapper.INSTANCE.toCommunityGardenDTO(communityGarden);
    }
}