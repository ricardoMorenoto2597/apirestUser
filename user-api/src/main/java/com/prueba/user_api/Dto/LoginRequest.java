package com.prueba.user_api.Dto;

import lombok.Data;
public class LoginRequest {
    private String taxId;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String taxId, String password) {
        this.taxId = taxId;
        this.password = password;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
