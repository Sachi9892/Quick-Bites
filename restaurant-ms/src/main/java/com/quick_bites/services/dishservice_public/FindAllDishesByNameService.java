package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllDishesByNameService {

    Page<ResponseDishDto> allDishesByName(String dishName , Pageable pageable);

}
