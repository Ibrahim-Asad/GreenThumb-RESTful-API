package com.greenthumb.controller;



import com.greenthumb.model.dto.KnowledgeResourceDTO;
import com.greenthumb.service.KnowledgeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledgeResources")
public class KnowledgeResourceController {

    private final KnowledgeResourceService knowledgeResourceService;

    @Autowired
    public KnowledgeResourceController(KnowledgeResourceService knowledgeResourceService) {
        this.knowledgeResourceService = knowledgeResourceService;
    }

    @GetMapping
    public ResponseEntity<List<KnowledgeResourceDTO>> getAllKnowledgeResources() {
        List<KnowledgeResourceDTO> resources = knowledgeResourceService.findAll();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgeResourceDTO> getKnowledgeResourceById(@PathVariable Long id) {
        KnowledgeResourceDTO resource = knowledgeResourceService.findById(id);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<KnowledgeResourceDTO> createKnowledgeResource(@RequestBody KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResourceDTO newResource = knowledgeResourceService.create(knowledgeResourceDTO);
        return ResponseEntity.ok(newResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KnowledgeResourceDTO> updateKnowledgeResource(@PathVariable Long id, @RequestBody KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResourceDTO updatedResource = knowledgeResourceService.update(id, knowledgeResourceDTO);
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKnowledgeResource(@PathVariable Long id) {
        knowledgeResourceService.delete(id);
        return ResponseEntity.ok().build();
    }
}

