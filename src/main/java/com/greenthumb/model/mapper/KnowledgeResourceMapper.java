package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.KnowledgeResourceDTO;
import com.greenthumb.model.entity.KnowledgeResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KnowledgeResourceMapper {

    KnowledgeResourceMapper INSTANCE = Mappers.getMapper(KnowledgeResourceMapper.class);

    KnowledgeResource toknowledgeResource(KnowledgeResourceDTO knowledgeResourceDTO);
    KnowledgeResourceDTO toKnowledgeResourceDTO(KnowledgeResource knowledgeResource);

}
