package com.greenthumb.repository;

import com.greenthumb.model.entity.WeatherResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherResourceRepo extends JpaRepository<WeatherResource,Long> {
}
