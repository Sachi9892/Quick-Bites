package com.quick_bites.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL ,orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

    private int totalDishes;

    private Double totalAmount;

    //Can be used for cache
    private LocalDateTime createdAt;

    public Cart(Long userId, List<CartItem> cartItems, int totalDishes, LocalDateTime now) {

        this.userId = userId;
        this.cartItems = cartItems;
        this.totalDishes = totalDishes;
        createdAt = now;


    }
}