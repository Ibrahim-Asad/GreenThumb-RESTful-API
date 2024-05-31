package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.CropPlanDTO;
import com.greenthumb.model.entity.CropPlan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CropPlanMapper {
    CropPlanMapper INSTANCE = Mappers.getMapper(CropPlanMapper.class);

    CropPlan tocropPlan(CropPlanDTO cropPlanDTO);
    CropPlanDTO tocropPlanDTO(CropPlan cropPlan);
}
