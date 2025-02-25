package com.quick_bites.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long userId;

    private Long restId;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.EAGER ,
            cascade = { CascadeType.PERSIST , CascadeType.REFRESH , CascadeType.MERGE }
            ,orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

    private int totalDishes;

    private Double totalAmount;

    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    public Cart(Long userId, Long restId, List<CartItem> cartItems, int totalDishes, Double totalAmount, LocalDateTime createdAt , CartStatus cartStatus) {
        this.userId = userId;
        this.restId = restId;
        this.cartItems = cartItems;
        this.totalDishes = totalDishes;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.status = cartStatus;
    }



}