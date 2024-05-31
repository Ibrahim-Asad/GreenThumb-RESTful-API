package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.ResourceExchangeDTO;
import com.greenthumb.model.entity.ResourceExchange;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResourceExchangeMapper {

    ResourceExchangeMapper INSTANCE = Mappers.getMapper(ResourceExchangeMapper.class);

    ResourceExchange toResourceExchange(ResourceExchangeDTO resourceExchangeDTO);
    ResourceExchangeDTO toResourceExchangeDTO(ResourceExchange resourceExchange);

}
