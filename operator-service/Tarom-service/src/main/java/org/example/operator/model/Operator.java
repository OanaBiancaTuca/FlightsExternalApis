package org.example.operator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "operators")
public class Operator {
    @Id
    private String id;

    private String name;
    private String code;
    private String IBAN;
    private String country;

    public Operator(String id, String name, String code, String IBAN, String country) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.IBAN = IBAN;
        this.country = country;
    }

    public Operator() {

    }

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
