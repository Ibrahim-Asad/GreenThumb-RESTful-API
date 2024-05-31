package com.greenthumb.repository;

import com.greenthumb.model.entity.ResourceExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceExchangeRepo extends JpaRepository<ResourceExchange,Long> {
}
