package com.management.controllers.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public class ProviderDTO {

    @Size(min = 5, message = "name not valid") @NotNull
    String name;

    @CNPJ @NotNull
    String cnpj;

    @Size(min = 11, max = 11, message = "o celular precisa ter 11 caractere")  @NotNull
    String cellphone;

    @NotNull
    String address;

    public ProviderDTO() {
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
