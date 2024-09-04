package com.quick_bites.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class RestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Range(min = 1, max = 5)
    private double rating;

    private String comment;
    private LocalDateTime reviewTime;

    //userId to map review with user
    private Long userId;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "DishReview{" +
                "reviewId=" + reviewId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", reviewTime=" + reviewTime +
                ", userId=" + userId +
                '}';
    }

}
