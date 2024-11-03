package com.quick_bites.dto.dish_dto;

import com.quick_bites.entity.DishType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class AddDishDto {

    private String dishName;

    private String category;

    private String description;

    private Double price;

    private DishType dishType;

    private MultipartFile dishPic;



}
