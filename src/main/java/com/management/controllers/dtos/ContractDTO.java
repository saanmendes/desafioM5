package com.management.controllers.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ContractDTO {

    @Id @NotNull
    Long id;

    @Min(3) @Max(3) @NotNull
    String numberOfTheContract;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    @Min(1) @NotNull
    float totalValue;

    @NotNull
    String description;

    @NotNull
    Long providerId;

    public ContractDTO() {
    }

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public @Min(3) @Max(3) @NotNull String getNumberOfTheContract() {
        return numberOfTheContract;
    }

    public void setNumberOfTheContract(@Min(3) @Max(3) @NotNull String numberOfTheContract) {
        this.numberOfTheContract = numberOfTheContract;
    }

    public @NotNull LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull LocalDate endDate) {
        this.endDate = endDate;
    }

    @Min(1)
    @NotNull
    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(@Min(1) @NotNull float totalValue) {
        this.totalValue = totalValue;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public @NotNull Long getProviderId() {
        return providerId;
    }

    public void setProviderId(@NotNull Long providerId) {
        this.providerId = providerId;
    }
}
