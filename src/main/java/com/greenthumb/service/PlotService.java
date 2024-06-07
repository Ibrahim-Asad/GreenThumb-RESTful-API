package com.greenthumb.service;


import com.greenthumb.model.dto.PlotDTO;

import java.util.List;

public interface PlotService {
    List<PlotDTO> findAll();
    PlotDTO findById(Long id);
    PlotDTO create(PlotDTO plotDTO);
    PlotDTO update(Long id, PlotDTO plotDTO);
    void delete(Long id);
}

