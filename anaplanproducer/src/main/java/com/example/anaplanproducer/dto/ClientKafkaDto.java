package com.example.anaplanproducer.dto;

import java.io.Serializable;

public class ClientKafkaDto implements Serializable {

    private String country;
    private String contactNumber;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "ClientKafkaDto{" +
                "country='" + country + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}

