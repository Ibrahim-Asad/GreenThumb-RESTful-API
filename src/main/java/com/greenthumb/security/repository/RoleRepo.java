package com.greenthumb.security.repository;

import com.greenthumb.security.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity,Long> {
}
