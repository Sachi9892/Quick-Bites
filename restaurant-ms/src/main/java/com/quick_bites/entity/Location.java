package com.quick_bites.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable {

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
