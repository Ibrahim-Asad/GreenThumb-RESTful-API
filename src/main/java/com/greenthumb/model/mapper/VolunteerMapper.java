package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.VolunteerActivityDTos;
import com.greenthumb.model.dto.VolunteerDTO;
import com.greenthumb.model.entity.Volunteer;
import com.greenthumb.model.entity.VolunteerActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VolunteerMapper {

    VolunteerMapper INSTANCE = Mappers.getMapper(VolunteerMapper.class);

    Volunteer toVolunteer(VolunteerDTO volunteerDTO);
    VolunteerDTO toVolunteerDTO(Volunteer volunteer);

    VolunteerActivityDTos toVolunteerActivityDTO(VolunteerActivity volunteerActivity);
    VolunteerActivity toVolunteerActivity(VolunteerActivityDTos volunteerActivityDTO);
}
