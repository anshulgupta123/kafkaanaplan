package com.example.anaplanconsumer.modal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clientdetails")
public class ClientDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
