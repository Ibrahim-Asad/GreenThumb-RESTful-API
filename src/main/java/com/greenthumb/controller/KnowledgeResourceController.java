package com.greenthumb.controller;



import com.greenthumb.model.dto.KnowledgeResourceDTO;
import com.greenthumb.service.KnowledgeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KnowledgeResourceController {

    private final KnowledgeResourceService knowledgeResourceService;

    @Autowired
    public KnowledgeResourceController(KnowledgeResourceService knowledgeResourceService) {
        this.knowledgeResourceService = knowledgeResourceService;
    }

    @GetMapping("/volunteer/knowledgeResources")
    public ResponseEntity<List<KnowledgeResourceDTO>> getAllKnowledgeResources() {
        List<KnowledgeResourceDTO> resources = knowledgeResourceService.findAll();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/volunteer/{id}/knowledgeResources")
    public ResponseEntity<KnowledgeResourceDTO> getKnowledgeResourceById(@PathVariable Long id) {
        KnowledgeResourceDTO resource = knowledgeResourceService.findById(id);
        return ResponseEntity.ok(resource);
    }

    @PostMapping("/admin/knowledgeResources")
    public ResponseEntity<KnowledgeResourceDTO> createKnowledgeResource(@RequestBody KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResourceDTO newResource = knowledgeResourceService.create(knowledgeResourceDTO);
        return ResponseEntity.ok(newResource);
    }

    @PutMapping("/admin/{id}/knowledgeResources")
    public ResponseEntity<KnowledgeResourceDTO> updateKnowledgeResource(@PathVariable Long id, @RequestBody KnowledgeResourceDTO knowledgeResourceDTO) {
        KnowledgeResourceDTO updatedResource = knowledgeResourceService.update(id, knowledgeResourceDTO);
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/admin/{id}/knowledgeResources")
    public ResponseEntity<Void> deleteKnowledgeResource(@PathVariable Long id) {
        knowledgeResourceService.delete(id);
        return ResponseEntity.ok().build();
    }
}

