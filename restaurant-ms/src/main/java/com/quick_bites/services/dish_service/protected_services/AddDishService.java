package com.quick_bites.services.dish_service.protected_services;

import com.quick_bites.dto.dish_dto.AddDishDto;

public interface AddDishService {

     String addDish(Long id , AddDishDto addDishDto);

}
