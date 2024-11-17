package com.quick_bites.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    private String dishName;
    private String description;
    private Double price;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private String dishPic;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;


    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;


    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DishReview> dishReviews;

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dishType=" + dishType +
                ", dishPic='" + dishPic + '\'' +
                ", restaurant=" + restaurant +
                ", category=" + category +
                ", dishReviews=" + dishReviews +
                '}';
    }

}
