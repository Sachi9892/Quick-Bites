package com.quick_bites.services.dish_service.protected_services;

import com.quick_bites.dto.dish_dto.AddDishDto;

public interface AddDishService {

     void addDish(Long id , AddDishDto addDishDto);

}
