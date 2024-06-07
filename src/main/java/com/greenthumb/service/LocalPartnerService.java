package com.greenthumb.service;


import com.greenthumb.model.dto.LocalPartnerDTO;

import java.util.List;

public interface LocalPartnerService {
    List<LocalPartnerDTO> findAll();
    LocalPartnerDTO findById(Long id);
    LocalPartnerDTO create(LocalPartnerDTO localPartnerDTO);
    LocalPartnerDTO update(Long id, LocalPartnerDTO localPartnerDTO);
    void delete(Long id);
}

