package com.greenthumb.repository;

import com.greenthumb.model.entity.CropPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropPlanRepo extends JpaRepository<CropPlan,Long> {
}
