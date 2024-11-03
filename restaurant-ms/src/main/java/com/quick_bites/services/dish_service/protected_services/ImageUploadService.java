package com.quick_bites.services.dish_service.protected_services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    String uploadImage(MultipartFile file, String restaurantName, String categoryName, String dishName) ;


}
