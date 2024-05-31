package com.greenthumb.repository;

import com.greenthumb.model.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepo extends JpaRepository<Plot,Long> {
}
