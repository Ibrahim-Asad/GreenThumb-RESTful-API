package com.greenthumb.service.impl;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.repository.CommunityGardenRepo;
import com.greenthumb.model.mapper.CommunityGardenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityGardenServiceImpl {

    private final CommunityGardenRepo communityGardenRepo;

    @Autowired
    public CommunityGardenServiceImpl(CommunityGardenRepo communityGardenRepo) {
        this.communityGardenRepo = communityGardenRepo;
    }

    public List<CommunityGardenDTO> getAllCommuntyGarden() {
        List<CommunityGarden> communityGardens = communityGardenRepo.findAll();
        return communityGardens.stream()
                .map(CommunityGardenMapper.INSTANCE::tocommunityGardenDTO)
                .collect(Collectors.toList());
    }
}