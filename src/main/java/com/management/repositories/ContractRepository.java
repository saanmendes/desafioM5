package com.management.repositories;

import com.management.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ContractRepository extends JpaRepository <Contract, Long> {

    List<Contract> findByProviderId(Long providerId);
}
