package com.quick_bites.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String userName;

    private String userEmail;

    private String userMobileNumber;

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    private LocalDateTime createdAt;

    private boolean isUserPremium = false;

    private LocalDateTime updatedAt;

    private String password;
}
