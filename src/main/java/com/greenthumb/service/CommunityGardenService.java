package com.greenthumb.service;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.repository.CommunityGardenRepo;
import com.greenthumb.model.mapper.CommunityGardenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityGardenService {

    private final CommunityGardenRepo communityGardenRepo;

    @Autowired
    public CommunityGardenService(CommunityGardenRepo communityGardenRepo) {
        this.communityGardenRepo = communityGardenRepo;
    }

    public List<CommunityGardenDTO> getAllCommuntyGarden() {
        List<CommunityGarden> communityGardens = communityGardenRepo.findAll();
        return communityGardens.stream()
                .map(CommunityGardenMapper.INSTANCE::tocommunityGardenDTO)
                .collect(Collectors.toList());
    }
}
