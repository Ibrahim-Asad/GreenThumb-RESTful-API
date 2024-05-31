package com.greenthumb.repository;

import com.greenthumb.model.entity.KnowledgeResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeResourceRepo extends JpaRepository<KnowledgeResource,Long> {
}
