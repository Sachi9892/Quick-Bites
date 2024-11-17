package com.quick_bites.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DishReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Range(min = 1, max = 5)
    private Double rating;

    private String comment;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime reviewTime;

    //UserId to map review with user
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    @JsonIgnore
    private Dish dish;

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
