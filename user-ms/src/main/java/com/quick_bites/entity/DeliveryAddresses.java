package com.quick_bites.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAddresses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryAddressId;

    private String name;

    private String plotNo;

    private String landmark;

    private Double latitude;

    private Double longitude;

    private String userAddress;

    @OneToMany(mappedBy = "deliveryAddress" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderRecord> orders;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


}
