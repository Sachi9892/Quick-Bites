package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;

import java.util.List;

public interface IFindAllCategories {

    List<ResponseCategoryDto> allCategories(String restName);

}
