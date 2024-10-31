package com.management.controllers;

import com.management.controllers.dtos.ProviderDTO;
import com.management.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public List<ProviderDTO> listAll() {
        return providerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> findById(@PathVariable Long id) {
        ProviderDTO providerDTO = providerService.findById(id);
        return providerDTO != null ? ResponseEntity.ok(providerDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@RequestBody ProviderDTO providerDTO) {
        ProviderDTO createdProvider = providerService.create(providerDTO);
        return ResponseEntity.status(201).body(createdProvider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@PathVariable Long id, @RequestBody ProviderDTO providerDTO) {
        ProviderDTO updatedProvider = providerService.update(id, providerDTO);
        return updatedProvider != null ? ResponseEntity.ok(updatedProvider) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean isDeleted = providerService.delete(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
