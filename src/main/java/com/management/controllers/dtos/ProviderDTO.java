package com.management.controllers.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public class ProviderDTO {
    @NotNull
    Long id;

    @Size(min = 5, message = "name not valid") @NotNull @NotBlank
    String name;

    @CNPJ @NotNull @NotBlank
    String cnpj;

    @Size(min = 11, max = 11, message = "o celular precisa ter 11 caractere")  @NotNull @NotBlank
    String cellphone;

    @NotNull @NotBlank
    String address;

    public ProviderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public  String getCnpj() {
        return cnpj;
    }

    public void setCnpj( String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address) {
        this.address = address;
    }
}
