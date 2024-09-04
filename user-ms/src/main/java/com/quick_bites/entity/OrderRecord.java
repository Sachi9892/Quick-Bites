package com.quick_bites.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long userId;

    private Long restaurantId;

    private Double totalPrice;

    private LocalDateTime orderDate;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany()
    private List<DeliveryAddresses> deliveryAddress;

    @OneToOne
    private Cart cart;

    @OneToOne
    private PaymentDetails paymentDetails;

    @OneToOne
    private DeliveryInfo deliveryInfo;

}
