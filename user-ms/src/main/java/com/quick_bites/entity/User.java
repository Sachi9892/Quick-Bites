package com.quick_bites.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String userEmail;

    private String userMobileNumber;

    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    private boolean isUserPremium = false;

    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference
    private List<UserDishReview> dishReviews;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference
    private List<UserRestaurantReview> restaurantReviews;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderRecord> orders;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DeliveryAddresses> deliveryAddresses;


}
