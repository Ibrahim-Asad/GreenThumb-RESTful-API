package com.greenthumb.secutity.repository;

import com.greenthumb.secutity.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity,Long> {
}
