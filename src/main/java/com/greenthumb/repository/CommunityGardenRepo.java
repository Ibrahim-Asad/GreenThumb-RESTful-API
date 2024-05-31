package com.greenthumb.repository;

import com.greenthumb.model.entity.CommunityGarden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityGardenRepo extends JpaRepository<CommunityGarden,Long> {
}
