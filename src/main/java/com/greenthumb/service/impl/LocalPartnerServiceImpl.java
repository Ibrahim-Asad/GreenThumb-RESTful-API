package com.greenthumb.service.impl;


import com.greenthumb.model.dto.LocalPartnerDTO;
import com.greenthumb.model.entity.LocalPartner;
import com.greenthumb.model.mapper.LocalPartnerMapper;
import com.greenthumb.repository.LocalPartnerRepo;
import com.greenthumb.service.LocalPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalPartnerServiceImpl implements LocalPartnerService {

    private final LocalPartnerRepo localPartnerRepo;

    @Autowired
    public LocalPartnerServiceImpl(LocalPartnerRepo localPartnerRepo) {
        this.localPartnerRepo = localPartnerRepo;
    }

    @Override
    public List<LocalPartnerDTO> findAll() {
        return localPartnerRepo.findAll().stream()
                .map(LocalPartnerMapper.INSTANCE::toLocalPartnerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocalPartnerDTO findById(Long id) {
        LocalPartner localPartner = localPartnerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Local partner not found"));
        return LocalPartnerMapper.INSTANCE.toLocalPartnerDTO(localPartner);
    }

    @Override
    public LocalPartnerDTO create(LocalPartnerDTO localPartnerDTO) {
        LocalPartner localPartner = LocalPartnerMapper.INSTANCE.toLocalPartner(localPartnerDTO);
        localPartner = localPartnerRepo.save(localPartner);
        return LocalPartnerMapper.INSTANCE.toLocalPartnerDTO(localPartner);
    }

    @Override
    public LocalPartnerDTO update(Long id, LocalPartnerDTO localPartnerDTO) {
        LocalPartner existingPartner = localPartnerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Local partner not found"));
        existingPartner.setName(localPartnerDTO.getName());
        existingPartner.setService(localPartnerDTO.getService());
        existingPartner.setEmail(localPartnerDTO.getEmail());
        existingPartner = localPartnerRepo.save(existingPartner);
        return LocalPartnerMapper.INSTANCE.toLocalPartnerDTO(existingPartner);
    }

    @Override
    public void delete(Long id) {
        LocalPartner localPartner = localPartnerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Local partner not found"));
        localPartnerRepo.delete(localPartner);
    }
}

