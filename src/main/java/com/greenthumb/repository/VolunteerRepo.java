package com.greenthumb.repository;

import com.greenthumb.model.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepo extends JpaRepository<Volunteer,Long> {
}
