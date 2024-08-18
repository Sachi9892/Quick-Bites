package com.quick_bites.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String userEmail;

    private String userMobileNumber;

    private String userAddress;

    private LocalDateTime createdAt;

    private boolean isUserPremium = false;

    private LocalDateTime updatedAt;

    private String password;
}
