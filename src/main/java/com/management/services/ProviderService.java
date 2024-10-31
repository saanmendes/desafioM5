package com.management.services;

import com.management.controllers.dtos.ProviderDTO;
import com.management.models.Provider;
import com.management.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Provider> provider = providerRepository.findById(id);
        return provider.map(this::convertToDTO).orElse(null);
    }

    public ProviderDTO create(ProviderDTO providerDTO) {
        Provider provider = convertToEntity(providerDTO);
        Provider savedProvider = providerRepository.save(provider);
        return convertToDTO(savedProvider);
    }

    public ProviderDTO update(Long id, ProviderDTO providerDTO) {
        Optional<Provider> existingProvider = providerRepository.findById(id);
        if (existingProvider.isPresent()) {
            Provider provider = convertToEntity(providerDTO);
            provider.setId(existingProvider.get().getId());
            Provider updatedProvider = providerRepository.save(provider);
            return convertToDTO(updatedProvider);
        }
        return null;
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
