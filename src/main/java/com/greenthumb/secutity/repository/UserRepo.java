package com.greenthumb.secutity.repository;

import com.greenthumb.secutity.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByUsername(String username);
}
