package com.greenthumb.repository;

import com.greenthumb.model.entity.VolunteerActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerActivityRepo extends JpaRepository<VolunteerActivity,Long> {
}
