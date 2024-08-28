package com.quick_bites.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews;


    private LocalDateTime createdAt;

    private boolean isUserPremium = false;

    private LocalDateTime updatedAt;

    private String password;
}
