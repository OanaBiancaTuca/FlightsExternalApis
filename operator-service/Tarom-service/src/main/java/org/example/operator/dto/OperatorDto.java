package org.example.operator.dto;

import jakarta.validation.constraints.NotNull;

public class OperatorDto {
    private String id;
    @NotNull(message = "Code cannot be null")
    private String name;
    @NotNull(message = "Code cannot be null")
    private String code;
    @NotNull(message = "Code cannot be null")
    private String IBAN;
    @NotNull(message = "Code cannot be null")
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
