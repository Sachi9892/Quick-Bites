package com.quick_bites.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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


    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddresses deliveryAddress;


    // Each order has one cart
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;


    // Each order has one payment
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
    private PaymentDetails paymentDetails;



    // Each order has one delivery info record
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_info_id", referencedColumnName = "infoId")
    private DeliveryInfo deliveryInfo;

}
