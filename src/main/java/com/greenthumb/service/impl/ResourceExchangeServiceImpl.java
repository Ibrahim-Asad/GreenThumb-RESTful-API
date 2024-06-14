package com.greenthumb.service.impl;


import com.greenthumb.model.dto.ResourceExchangeDTO;
import com.greenthumb.model.entity.ResourceExchange;
import com.greenthumb.model.mapper.ResourceExchangeMapper;
import com.greenthumb.repository.ResourceExchangeRepo;
import com.greenthumb.security.model.entity.UserEntity;
import com.greenthumb.security.repository.UserRepo;
import com.greenthumb.service.ResourceExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceExchangeServiceImpl implements ResourceExchangeService {

    private final ResourceExchangeRepo resourceExchangeRepo;
    private final UserRepo userRepo;

    @Autowired
    public ResourceExchangeServiceImpl(ResourceExchangeRepo resourceExchangeRepo,UserRepo userRepo) {
        this.resourceExchangeRepo = resourceExchangeRepo;
        this.userRepo=userRepo;
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

    @Override
    @Transactional
    public ResourceExchangeDTO decrementQuantity(Long resourceId, Long userId, int amount) {
        ResourceExchange resourceExchange = resourceExchangeRepo.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource exchange not found"));
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        resourceExchange.setQuantity(resourceExchange.getQuantity() - amount);

        if (!resourceExchange.getUsers().contains(user)) {
            resourceExchange.getUsers().add(user);
        }

        resourceExchange = resourceExchangeRepo.save(resourceExchange);
        return ResourceExchangeMapper.INSTANCE.toResourceExchangeDTO(resourceExchange);
    }

    @Override
    @Transactional
    public ResourceExchangeDTO incrementQuantity(Long resourceId, Long userId, int amount) {
        ResourceExchange resourceExchange = resourceExchangeRepo.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource exchange not found"));
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        resourceExchange.setQuantity(resourceExchange.getQuantity() + amount);

        if (!resourceExchange.getUsers().contains(user)) {
            resourceExchange.getUsers().add(user);
        }

        resourceExchange = resourceExchangeRepo.save(resourceExchange);
        return ResourceExchangeMapper.INSTANCE.toResourceExchangeDTO(resourceExchange);
    }
}

