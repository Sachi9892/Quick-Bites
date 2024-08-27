package com.quick_bites.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;

    private double latitude;
    private double longitude;
    private String userAddress;


    public Address(double latitude, double longitude, String userAddress) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.userAddress = userAddress;
    }
}
