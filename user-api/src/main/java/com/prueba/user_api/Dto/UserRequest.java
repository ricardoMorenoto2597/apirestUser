package com.prueba.user_api.Dto;

import com.prueba.user_api.Dto.AddressRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class UserRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @Pattern(regexp = "^\\+?[0-9]{10,13}$")
    private String phone;

    @NotBlank
    private String password;

    @Pattern(regexp = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$")
    private String taxId;

    private List<AddressRequest> addresses;

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }
}
