package com.management.services;

import com.management.controllers.dtos.ContractDTO;
import com.management.models.Contract;
import com.management.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public List<ContractDTO> findContractsByProvider(String fornecedorId, String startDate, Boolean active, String endDate, String descricao) {
        List<Contract> contracts = contractRepository.findByProviderId(Long.parseLong(fornecedorId));

        if (startDate != null) {
            LocalDate parsedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
            contracts = contracts.stream()
                    .filter(contract -> contract.getStartDate().equals(parsedStartDate))
                    .collect(Collectors.toList());
        }
        if (active != null) {
            contracts = contracts.stream()
                    .filter(contract -> contract.isActive() == active)
                    .collect(Collectors.toList());
        }
        if (endDate != null) {
            LocalDate parsedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
            contracts = contracts.stream()
                    .filter(contract -> contract.getEndDate().equals(parsedEndDate))
                    .collect(Collectors.toList());
        }
        if (descricao != null && !descricao.isEmpty()) {
            contracts = contracts.stream()
                    .filter(contract -> contract.getDescription().contains(descricao))
                    .collect(Collectors.toList());
        }

        return contracts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public List<ContractDTO> findAll() {
        return contractRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ContractDTO findById(String id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.map(this::convertToDTO).orElse(null);
    }

    public ContractDTO saveContract(String providerId, ContractDTO contractDTO) {
        validateContractDates(contractDTO);

        Contract contract = convertToEntity(contractDTO);
        LocalDate endDate = contractDTO.getEndDate();

        boolean isActive = contractRepository.findByProviderId(Long.parseLong(providerId)).stream()
                .anyMatch(existingContract -> existingContract.getEndDate().isAfter(LocalDate.now()));

        contract.setActive(isActive && endDate.isAfter(LocalDate.now()));

        Contract savedContract = contractRepository.save(contract);
        return convertToDTO(savedContract);
    }


    public ContractDTO updateContract(String id, ContractDTO contractDTO) {
        validateContractDates(contractDTO);

        Optional<Contract> existingContract = contractRepository.findById(id);
        if (!existingContract.isPresent()) {
            return null;
        }
        Contract contract = convertToEntity(contractDTO);
        contract.setId(existingContract.get().getId());

        Contract updatedContract = contractRepository.save(contract);
        return convertToDTO(updatedContract);
    }


    public boolean deleteContract(String id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateContractDates(ContractDTO contractDTO) {
        LocalDate startDate = contractDTO.getStartDate();
        LocalDate endDate = contractDTO.getEndDate();

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("As datas de início e término não podem estar vazias");
        }

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("A data de término deve ser maior que a data de início.");
        }
    }


    private ContractDTO convertToDTO(Contract contract) {
        ContractDTO dto = new ContractDTO();
        dto.setId(contract.getId());
        dto.setNumberOfTheContract(contract.getNumberOfTheContract());
        dto.setStartDate(contract.getStartDate());
        dto.setEndDate(contract.getEndDate());
        dto.setTotalValue(contract.getTotalValue());
        dto.setDescription(contract.getDescription());
        return dto;
    }

    private Contract convertToEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setNumberOfTheContract(dto.getNumberOfTheContract());
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setTotalValue(dto.getTotalValue());
        contract.setDescription(dto.getDescription());
        return contract;
    }
}