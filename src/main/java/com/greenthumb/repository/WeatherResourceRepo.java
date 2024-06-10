package com.greenthumb.repository;

import com.greenthumb.model.entity.CommunityGarden;
import com.greenthumb.model.entity.WeatherResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WeatherResourceRepo extends JpaRepository<WeatherResource, Long> {
    Optional<WeatherResource> findByCommunityGarden(CommunityGarden communityGarden);
}
