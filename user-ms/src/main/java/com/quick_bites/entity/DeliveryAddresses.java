package com.quick_bites.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAddresses {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryAddressId;

    private String name;

    private String plotNo;

    private String landmark;

    @OneToMany(mappedBy = "deliveryAddress")
    private List<OrderRecord> orders;

}
