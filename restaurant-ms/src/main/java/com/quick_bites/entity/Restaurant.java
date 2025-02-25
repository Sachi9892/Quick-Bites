package com.quick_bites.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restId;

    private String restaurantName;
    private String mobileNumber;
    private String restPic;

    @OneToOne(cascade = CascadeType.PERSIST , orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Location location;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Dish> dishes;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Category> categories;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<RestaurantReview> restReviews;



}
