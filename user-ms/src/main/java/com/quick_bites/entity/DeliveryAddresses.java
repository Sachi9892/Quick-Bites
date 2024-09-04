package com.quick_bites.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAddresses {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryAddId;

    private String name;

    private String plotNo;

    private String landmark;

}
