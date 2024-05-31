package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.CommunityGardenDTO;
import com.greenthumb.model.entity.CommunityGarden;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommunityGardenMapper {

    CommunityGardenMapper INSTANCE = Mappers.getMapper(CommunityGardenMapper.class);

    CommunityGardenDTO tocommunityGardenDTO(CommunityGarden communityGarden);
    CommunityGarden  tocommunityGarden(CommunityGardenDTO communityGardenDTO);

}
