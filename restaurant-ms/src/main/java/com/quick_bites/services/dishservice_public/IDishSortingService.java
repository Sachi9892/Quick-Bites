package com.quick_bites.services.dishservice_public;

import com.quick_bites.entity.Dish;

import java.util.List;

public interface IDishSortingService {

     List<Dish> sortDishes(List<Dish> dishes, String sortBy, boolean ascending, Double userLatitude, Double userLongitude , Double minDistance , Double maxDistance);

}
