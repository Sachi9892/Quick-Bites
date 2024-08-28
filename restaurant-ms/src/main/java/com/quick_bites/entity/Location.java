package com.quick_bites.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Location {

    public Location(double latitude , double longitude , String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private double latitude;
    private double longitude;
    private String address;


    @OneToOne(mappedBy = "location")
    private Restaurant restaurant;

}
