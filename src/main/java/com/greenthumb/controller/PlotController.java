package com.greenthumb.controller;


import com.greenthumb.model.dto.PlotDTO;
import com.greenthumb.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlotController {

    private final PlotService plotService;

    @Autowired
    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @GetMapping("/volunteer/plots")
    public ResponseEntity<List<PlotDTO>> getAllPlots() {
        List<PlotDTO> plots = plotService.findAll();
        return ResponseEntity.ok(plots);
    }

    @GetMapping("/volunteer/{id}/plots")
    public ResponseEntity<PlotDTO> getPlotById(@PathVariable Long id) {
        PlotDTO plot = plotService.findById(id);
        return ResponseEntity.ok(plot);
    }

    @PostMapping("/admin/plots")
    public ResponseEntity<PlotDTO> createPlot(@RequestBody PlotDTO plotDTO) {
        PlotDTO newPlot = plotService.create(plotDTO);
        return ResponseEntity.ok(newPlot);
    }

    @PutMapping("/admin/{id}/plots")
    public ResponseEntity<PlotDTO> updatePlot(@PathVariable Long id, @RequestBody PlotDTO plotDTO) {
        PlotDTO updatedPlot = plotService.update(id, plotDTO);
        return ResponseEntity.ok(updatedPlot);
    }

    @DeleteMapping("/admin/{id}/plots")
    public ResponseEntity<Void> deletePlot(@PathVariable Long id) {
        plotService.delete(id);
        return ResponseEntity.ok().build();
    }
}

