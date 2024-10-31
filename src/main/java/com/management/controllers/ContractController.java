package com.management.controllers;

import com.management.controllers.dtos.ContractDTO;
import com.management.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/provider/{fornecedorId}/contracts")
    public List<ContractDTO> listContractsByProvider(
            @PathVariable String fornecedorId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String description) {

        return contractService.findContractsByProvider(fornecedorId, startDate, active, endDate, description);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> findById(@PathVariable String id) {
        ContractDTO contractDTO = contractService.findById(id);
        return contractDTO != null ? ResponseEntity.ok(contractDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/contract")
    public ResponseEntity<ContractDTO> createContract(@PathVariable String fornecedorId, @RequestBody ContractDTO contractDTO) {
        ContractDTO savedContract = contractService.saveContract(fornecedorId, contractDTO);
        return ResponseEntity.status(201).body(savedContract);
    }

    @PutMapping("/contract/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable String id, @RequestBody ContractDTO contractDTO) {
        ContractDTO updatedContract = contractService.updateContract(id, contractDTO);
        return updatedContract != null ? ResponseEntity.ok(updatedContract) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/contract/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable String id) {
        boolean isDeleted = contractService.deleteContract(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}