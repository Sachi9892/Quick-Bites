package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.entity.Category;
import com.quick_bites.repository.category_repo.CategoryRepository;
import com.quick_bites.services.restaurant_service.IFindAllCategories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
public class FindAllCategoriesImpl implements IFindAllCategories {

    private final CategoryRepository categoryRepository;

    @Override
    public Set<ResponseCategoryDto> allCategories(String restName) {

        Set<Category> categories = categoryRepository.findAllByRestaurantName(restName);

        return categories.stream()
                .map(category -> new ResponseCategoryDto(category.getCategoryName() , category.getDescription()))
                .collect(Collectors.toSet());

    }

}
