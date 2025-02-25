package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class DishTypeFilterService {

    public List<Dish> filterByType(List<Dish> dishes, DishType dishType) {
        if (dishType == null) return dishes;

        List<Dish> list = dishes.stream()
                .filter(dish -> dish.getDishType().equals(dishType))
                .toList();

        log.info("Inside rating filter no of dishes : {} " , list.size());
        return list;
    }

}
