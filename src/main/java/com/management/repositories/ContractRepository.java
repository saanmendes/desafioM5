package com.management.repositories;

import com.management.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ContractRepository extends JpaRepository <Contract, String> {

    @Query("SELECT c FROM Contract c WHERE c.endDate > CURRENT_DATE")
    List<Contract> findActiveContracts();

    List<Contract> findByProviderId(Long providerId);
}