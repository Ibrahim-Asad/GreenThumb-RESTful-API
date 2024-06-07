package com.greenthumb.controller;


import com.greenthumb.model.dto.ResourceExchangeDTO;
import com.greenthumb.service.ResourceExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resourceExchanges")
public class ResourceExchangeController {

    private final ResourceExchangeService resourceExchangeService;

    @Autowired
    public ResourceExchangeController(ResourceExchangeService resourceExchangeService) {
        this.resourceExchangeService = resourceExchangeService;
    }

    @GetMapping
    public ResponseEntity<List<ResourceExchangeDTO>> getAllResourceExchanges() {
        List<ResourceExchangeDTO> exchanges = resourceExchangeService.findAll();
        return ResponseEntity.ok(exchanges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceExchangeDTO> getResourceExchangeById(@PathVariable Long id) {
        ResourceExchangeDTO exchange = resourceExchangeService.findById(id);
        return ResponseEntity.ok(exchange);
    }

    @PostMapping
    public ResponseEntity<ResourceExchangeDTO> createResourceExchange(@RequestBody ResourceExchangeDTO resourceExchangeDTO) {
        ResourceExchangeDTO newExchange = resourceExchangeService.create(resourceExchangeDTO);
        return ResponseEntity.ok(newExchange);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceExchangeDTO> updateResourceExchange(@PathVariable Long id, @RequestBody ResourceExchangeDTO resourceExchangeDTO) {
        ResourceExchangeDTO updatedExchange = resourceExchangeService.update(id, resourceExchangeDTO);
        return ResponseEntity.ok(updatedExchange);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourceExchange(@PathVariable Long id) {
        resourceExchangeService.delete(id);
        return ResponseEntity.ok().build();
    }
}

