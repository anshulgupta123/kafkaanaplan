package com.example.anaplanproducer.dto;


import java.io.Serializable;

public class ClientRequestDto implements Serializable {

    private Long clientId;
    private String clientName;
    private String country;
    private String contactNumber;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

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
        return "ClientRequestDto{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", country='" + country + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
