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

    private String mobileNumber;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private RiderStatus status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_address_id", referencedColumnName = "id")
    private CurrentAddress currentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_location_id", referencedColumnName = "id")
    private LastLocationAddress lastLocation;

    private String currentOrderId;

}