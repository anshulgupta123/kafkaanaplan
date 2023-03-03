package com.example.anaplanconsumer.dto;

public class ClientDetailsResponseDto {

    private Long clientDetailsId;
    private String country;
    private String contactNumber;


    public Long getClientDetailsId() {
        return clientDetailsId;
    }

    public void setClientDetailsId(Long clientDetailsId) {
        this.clientDetailsId = clientDetailsId;
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
        return "ClientDetailsResponseDto{" +
                "clientDetailsId=" + clientDetailsId +
                ", country='" + country + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
