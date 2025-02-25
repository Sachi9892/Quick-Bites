package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PriceFilterService {

    public List<Dish> filterByPrice(List<Dish> dishes, Double minPrice, Double maxPrice) {
        if (minPrice == null && maxPrice == null) return dishes;
        List<Dish> list = dishes.stream()
                .filter(dish -> (minPrice == null || dish.getPrice() >= minPrice) &&
                        (maxPrice == null || dish.getPrice() <= maxPrice))
                .toList();

        log.info("Inside price filter no of dishes : {} " , list.size());
        return list;
    }

}
