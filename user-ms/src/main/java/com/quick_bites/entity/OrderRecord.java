package com.quick_bites.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    private Long customerId;

    private Long restId;

    private Double totalAmount;


    @CreationTimestamp
    private LocalDateTime orderDate;


    @Enumerated(EnumType.STRING)
    private OrderType orderType;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    // For storing the scheduled time
    private LocalDateTime scheduledTime;


    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    @JsonBackReference
    private DeliveryAddresses deliveryAddress;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;



    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
    private PaymentDetails paymentDetails;



    @Override
    public String toString() {
        return "OrderRecord{" +
                "orderDate=" + orderDate +
                ", orderId=" + orderId +
                ", cartId=" + cart.getCartId() +
                '}';
    }

}
