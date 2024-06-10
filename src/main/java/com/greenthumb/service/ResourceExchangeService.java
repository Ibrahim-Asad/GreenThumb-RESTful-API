package com.greenthumb.service;

import com.greenthumb.model.dto.ResourceExchangeDTO;

import java.util.List;

public interface ResourceExchangeService {
    List<ResourceExchangeDTO> findAll();
    ResourceExchangeDTO findById(Long id);
    ResourceExchangeDTO create(ResourceExchangeDTO resourceExchangeDTO);
    ResourceExchangeDTO update(Long id, ResourceExchangeDTO resourceExchangeDTO);
    void delete(Long id);
    ResourceExchangeDTO decrementQuantity(Long resourceId, Long userId, int amount);
    ResourceExchangeDTO incrementQuantity(Long resourceId, Long userId, int amount);
}

