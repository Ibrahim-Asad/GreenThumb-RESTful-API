package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.VolunteerActivityDTO;
import com.greenthumb.model.entity.VolunteerActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface VolunteerActivityMapper {

    VolunteerActivityMapper INSTANCE = Mappers.getMapper(VolunteerActivityMapper.class);

    VolunteerActivity toVolunteerActivity(VolunteerActivityDTO volunteerActivityDTO);
    VolunteerActivityDTO toVolunteerActivityDTO(VolunteerActivity volunteerActivity);
}
