package com.greenthumb.service;


import com.greenthumb.model.dto.KnowledgeResourceDTO;

import java.util.List;

public interface KnowledgeResourceService {
    List<KnowledgeResourceDTO> findAll();
    KnowledgeResourceDTO findById(Long id);
    KnowledgeResourceDTO create(KnowledgeResourceDTO knowledgeResourceDTO);
    KnowledgeResourceDTO update(Long id, KnowledgeResourceDTO knowledgeResourceDTO);
    void delete(Long id);
}
