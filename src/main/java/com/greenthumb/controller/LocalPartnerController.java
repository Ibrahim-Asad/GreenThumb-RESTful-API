package com.greenthumb.controller;


import com.greenthumb.model.dto.LocalPartnerDTO;
import com.greenthumb.service.LocalPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocalPartnerController {

    private final LocalPartnerService localPartnerService;

    @Autowired
    public LocalPartnerController(LocalPartnerService localPartnerService) {
        this.localPartnerService = localPartnerService;
    }

    @GetMapping("/user/localPartners")
    public ResponseEntity<List<LocalPartnerDTO>> getAllLocalPartners() {
        List<LocalPartnerDTO> partners = localPartnerService.findAll();
        return ResponseEntity.ok(partners);
    }

    @GetMapping("/user/{id}/localPartners")
    public ResponseEntity<LocalPartnerDTO> getLocalPartnerById(@PathVariable Long id) {
        LocalPartnerDTO partner = localPartnerService.findById(id);
        return ResponseEntity.ok(partner);
    }

    @PostMapping("/admin/localPartners")
    public ResponseEntity<LocalPartnerDTO> createLocalPartner(@RequestBody LocalPartnerDTO localPartnerDTO) {
        LocalPartnerDTO newPartner = localPartnerService.create(localPartnerDTO);
        return ResponseEntity.ok(newPartner);
    }

    @PutMapping("/admin/{id}/localPartners")
    public ResponseEntity<LocalPartnerDTO> updateLocalPartner(@PathVariable Long id, @RequestBody LocalPartnerDTO localPartnerDTO) {
        LocalPartnerDTO updatedPartner = localPartnerService.update(id, localPartnerDTO);
        return ResponseEntity.ok(updatedPartner);
    }

    @DeleteMapping("/admin/{id}/localPartners")
    public ResponseEntity<Void> deleteLocalPartner(@PathVariable Long id) {
        localPartnerService.delete(id);
        return ResponseEntity.ok().build();
    }
}

