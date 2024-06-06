package com.greenthumb.security.repository;

import com.greenthumb.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByUsername(String username);
    @Query("SELECT DISTINCT u FROM UserEntity u LEFT JOIN FETCH u.communityGardens LEFT JOIN FETCH u.volunteerActivity")
    List<UserEntity> findAllWithDetails();

}
