package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.entity.Category;
import com.quick_bites.repository.category_repo.CategoryRepository;
import com.quick_bites.services.restaurant_service.IFindAllCategories;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class FindAllCategoriesImpl implements IFindAllCategories {

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(value = "rest_categories", key = "#restName")
    public List<ResponseCategoryDto> allCategories(String restName) {

        List<Category> categories = categoryRepository.findAllByRestaurantName(restName);

        return categories.stream()
                .map(category -> new ResponseCategoryDto(category.getCategoryName() , category.getDescription()))
                .toList();

    }

}
