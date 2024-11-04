package com.quick_bite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Entity(name = "LastAddress")
@AllArgsConstructor
@Builder
public class LastLocationAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double latitude;
    private Double longitude;
    private String name;
    private String landmark;
    private String pinCode;

}
