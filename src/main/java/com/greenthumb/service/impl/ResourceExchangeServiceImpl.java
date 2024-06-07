package com.greenthumb.service.impl;


import com.greenthumb.model.dto.ResourceExchangeDTO;
import com.greenthumb.model.entity.ResourceExchange;
import com.greenthumb.model.mapper.ResourceExchangeMapper;
import com.greenthumb.repository.ResourceExchangeRepo;
import com.greenthumb.service.ResourceExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceExchangeServiceImpl implements ResourceExchangeService {

    private final ResourceExchangeRepo resourceExchangeRepo;

    @Autowired
    public ResourceExchangeServiceImpl(ResourceExchangeRepo resourceExchangeRepo) {
        this.resourceExchangeRepo = resourceExchangeRepo;
    }

    @Override
    public List<ResourceExchangeDTO> findAll() {
        return resourceExchangeRepo.findAll().stream()
                .map(ResourceExchangeMapper.INSTANCE::toResourceExchangeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceExchangeDTO findById(Long id) {
        ResourceExchange resourceExchange = resourceExchangeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource exchange not found"));
        return ResourceExchangeMapper.INSTANCE.toResourceExchangeDTO(resourceExchange);
    }

    @Override
    public ResourceExchangeDTO create(ResourceExchangeDTO resourceExchangeDTO) {
        ResourceExchange resourceExchange = ResourceExchangeMapper.INSTANCE.toResourceExchange(resourceExchangeDTO);
        resourceExchange = resourceExchangeRepo.save(resourceExchange);
        return ResourceExchangeMapper.INSTANCE.toResourceExchangeDTO(resourceExchange);
    }

    @Override
    public ResourceExchangeDTO update(Long id, ResourceExchangeDTO resourceExchangeDTO) {
        ResourceExchange existingExchange = resourceExchangeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource exchange not found"));
        existingExchange.setResourceType(resourceExchangeDTO.getResourceType());
        existingExchange.setDescription(resourceExchangeDTO.getDescription());
        existingExchange = resourceExchangeRepo.save(existingExchange);
        return ResourceExchangeMapper.INSTANCE.toResourceExchangeDTO(existingExchange);
    }

    @Override
    public void delete(Long id) {
        ResourceExchange resourceExchange = resourceExchangeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource exchange not found"));
        resourceExchangeRepo.delete(resourceExchange);
    }
}

