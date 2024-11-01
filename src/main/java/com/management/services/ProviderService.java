package com.management.services;

import com.management.controllers.dtos.ProviderDTO;
import com.management.models.Provider;
import com.management.repositories.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<ProviderDTO> findAll() {
        return providerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProviderDTO findById(Long id) {
        return providerRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public ProviderDTO create(ProviderDTO providerDTO) {
        Provider provider = convertToEntity(providerDTO);
        try {
            Provider savedProvider = providerRepository.save(provider);
            return convertToDTO(savedProvider);
        } catch (DataAccessException e) {

            throw new RuntimeException("Erro ao salvar o fornecedor", e);
        }
    }

    public ProviderDTO update(Long id, ProviderDTO providerDTO) {
        Provider existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));

        Provider provider = convertToEntity(providerDTO);
        provider.setId(existingProvider.getId());

        try {
            Provider updatedProvider = providerRepository.save(provider);
            return convertToDTO(updatedProvider);
        } catch (DataAccessException e) {
            // Tratar a exceção de acesso a dados
            throw new RuntimeException("Erro ao atualizar o provedor", e);
        }
    }

    public boolean delete(Long id) {
        if (providerRepository.existsById(id)) {
            providerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProviderDTO convertToDTO(Provider provider) {
        ProviderDTO dto = new ProviderDTO();
        dto.setId(provider.getId());
        dto.setName(provider.getName());
        dto.setCnpj(provider.getCnpj());
        dto.setCellphone(provider.getCellphone());
        dto.setAddress(provider.getAddress());
        return dto;
    }

    private Provider convertToEntity(ProviderDTO dto) {
        Provider provider = new Provider();
        provider.setName(dto.getName());
        provider.setCnpj(dto.getCnpj());
        provider.setCellphone(dto.getCellphone());
        provider.setAddress(dto.getAddress());
        return provider;
    }
}
