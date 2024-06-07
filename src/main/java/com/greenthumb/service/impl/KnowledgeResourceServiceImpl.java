package com.greenthumb.service.impl;

//package com.greenthumb.model.service.impl;

import com.greenthumb.model.dto.KnowledgeResourceDTO;
import com.greenthumb.model.entity.KnowledgeResource;
import com.greenthumb.model.mapper.KnowledgeResourceMapper;
import com.greenthumb.repository.KnowledgeResourceRepo;
import com.greenthumb.service.KnowledgeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeResourceServiceImpl implements KnowledgeResourceService {

    private final KnowledgeResourceRepo knowledgeResourceRepo;

    @Autowired
    public KnowledgeResourceServiceImpl(KnowledgeResourceRepo knowledgeResourceRepo) {
        this.knowledgeResourceRepo = knowledgeResourceRepo;
    }

    @Override
    public List<KnowledgeResourceDTO> findAll() {
        return knowledgeResourceRepo.findAll().stream()
                .map(KnowledgeResourceMapper.INSTANCE::toKnowledgeResourceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KnowledgeResourceDTO findById(Long id) {
        KnowledgeResource knowledgeResource = knowledgeResourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Knowledge resource not found"));
        return KnowledgeResourceMapper.INSTANCE.toKnowledgeResourceDTO(knowledgeResource);
    }

    @Override
    public KnowledgeResourceDTO create(KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResource knowledgeResource = KnowledgeResourceMapper.INSTANCE.toknowledgeResource(knowledgeResourceDTO);
        knowledgeResource = knowledgeResourceRepo.save(knowledgeResource);
        return KnowledgeResourceMapper.INSTANCE.toKnowledgeResourceDTO(knowledgeResource);
    }

    @Override
    public KnowledgeResourceDTO update(Long id, KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResource existingResource = knowledgeResourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Knowledge resource not found"));
        existingResource.setTitle(knowledgeResourceDTO.getTitle());
        existingResource.setContent(knowledgeResourceDTO.getContent());
        existingResource = knowledgeResourceRepo.save(existingResource);
        return KnowledgeResourceMapper.INSTANCE.toKnowledgeResourceDTO(existingResource);
    }

    @Override
    public void delete(Long id) {
        KnowledgeResource resource = knowledgeResourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Knowledge resource not found"));
        knowledgeResourceRepo.delete(resource);
    }
}

