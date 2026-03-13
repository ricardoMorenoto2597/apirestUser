package com.prueba.user_api.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;



import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;

    private String email;

    private String name;

    private String phone;

    @JsonIgnore
    private String password;

    private String taxId;

    private String createdAt;

    private List<Address> address;

    public User(UUID id, String email, String name, String phone, String password, String taxId, String createdAt, List<Address> address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.taxId = taxId;
        this.createdAt = createdAt;
        this.address = address;
    }
    public User() {

    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }


}
