package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.LocalPartnerDTO;
import com.greenthumb.model.entity.LocalPartner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocalPartnerMapper {

    LocalPartnerMapper INSTANCE = Mappers.getMapper(LocalPartnerMapper.class);

    LocalPartner toLocalPartner(LocalPartnerDTO localPartnerDTO);
    LocalPartnerDTO toLocalPartnerDTO(LocalPartner localPartner);
}
