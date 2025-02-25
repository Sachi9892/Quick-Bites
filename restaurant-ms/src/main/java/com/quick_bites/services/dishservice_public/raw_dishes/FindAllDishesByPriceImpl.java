package com.quick_bites.services.dishservice_public.raw_dishes;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.IFindAllDishesByPrice;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllDishesByPriceImpl implements IFindAllDishesByPrice {

    private final DishRepository dishRepository;

    @Override
    @Cacheable(value = "dishes_by_price", key = "{#minPrice , #maxPrice}")
    public List<ResponseDishDto> dishesByPrice(Double minPrice, Double maxPrice) {
        List<Dish> dishes = dishRepository.findAllByPriceBetween(minPrice, maxPrice);
        return dishes.stream().map(DishMapper::mapToDto).toList();
    }
}
