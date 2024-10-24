package com.quick_bite.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Riders")
@Data  @AllArgsConstructor  @NoArgsConstructor
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long riderId;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private RiderStatus status;

    private Double latitude;

    private Double longitude;

    private String currentOrderId;

    private Double lastLocation;


}
