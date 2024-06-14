package com.greenthumb.service;


import com.greenthumb.model.dto.CommunityGardenDTO;

import java.util.List;

public interface CommunityGardenService {
    List<CommunityGardenDTO> findAll();
    CommunityGardenDTO findById(Long id);
    CommunityGardenDTO create(CommunityGardenDTO communityGardenDTO);
    CommunityGardenDTO update(Long id, CommunityGardenDTO communityGardenDTO);
    void delete(Long id);
    void addUserToCommunityGarden(Long gardenId, Long userId);
    CommunityGardenDTO getCommunityGardenWithUsers(Long gardenId);
}
