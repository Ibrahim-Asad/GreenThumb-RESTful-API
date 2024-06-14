package com.greenthumb.service.impl;


import com.greenthumb.model.dto.PlotDTO;
import com.greenthumb.model.entity.Plot;
import com.greenthumb.model.mapper.PlotMapper;
import com.greenthumb.repository.PlotRepo;
import com.greenthumb.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlotServiceImpl implements PlotService {

    private final PlotRepo plotRepo;

    @Autowired
    public PlotServiceImpl(PlotRepo plotRepo) {
        this.plotRepo = plotRepo;
    }

    @Override
    public List<PlotDTO> findAll() {
        return plotRepo.findAll().stream()
                .map(PlotMapper.INSTANCE::toPlotDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlotDTO findById(Long id) {
        Plot plot = plotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plot not found"));
        return PlotMapper.INSTANCE.toPlotDTO(plot);
    }

    @Override
    public PlotDTO create(PlotDTO plotDTO) {
        Plot plot = PlotMapper.INSTANCE.toPlot(plotDTO);
        plot = plotRepo.save(plot);
        return PlotMapper.INSTANCE.toPlotDTO(plot);
    }

    @Override
    public PlotDTO update(Long id, PlotDTO plotDTO) {
        Plot existingPlot = plotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plot not found"));
        existingPlot.setPlotName(plotDTO.getPlotName());
        existingPlot.setSunlight(plotDTO.getSunlight());
        existingPlot.setSoilType(plotDTO.getSoilType());
        existingPlot = plotRepo.save(existingPlot);
        return PlotMapper.INSTANCE.toPlotDTO(existingPlot);
    }

    @Override
    public void delete(Long id) {
        Plot plot = plotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plot not found"));
        plotRepo.delete(plot);
    }
}

