package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.VolunteerDTO;
import com.greenthumb.model.entity.Volunteer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface VolunteerMapper {

    VolunteerMapper INSTANCE = Mappers.getMapper(VolunteerMapper.class);

    Volunteer toVolunteer(VolunteerDTO volunteerDTO);
    VolunteerDTO toVolunteerDTO(Volunteer volunteer);
}
