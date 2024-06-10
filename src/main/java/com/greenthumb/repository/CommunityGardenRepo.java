package com.greenthumb.repository;

import com.greenthumb.model.entity.CommunityGarden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommunityGardenRepo extends JpaRepository<CommunityGarden,Long> {
    @Query("SELECT cg FROM CommunityGarden cg LEFT JOIN FETCH cg.users WHERE cg.id = :id")
    Optional<CommunityGarden> findByIdWithUsers(@Param("id") Long id);
}
