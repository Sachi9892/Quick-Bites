package com.quick_bites.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long customerId;

    private Long restId;

    private Double totalAmount;


    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime orderDate;


    @Enumerated(EnumType.STRING)
    private OrderType orderType;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    // For storing the scheduled time
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalTime scheduledTime;


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


    // Helper method to get the Razorpay order ID from linked PaymentDetails
    public String getRazorpayOrderId() {
        if (this.paymentDetails != null) {
            return this.paymentDetails.getRazorpayOrderId();
        }
        return null;
    }



}
