package com.quick_bites.services.dish_service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.quick_bites.constants.AppConstants;
import com.quick_bites.exception.ImageUploadException;
import com.quick_bites.services.dish_service.protected_services.ImageUploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class IImageUploadService implements ImageUploadService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file, String restaurantName, String categoryName, String dishName) {
        try {
            // Define a custom public ID
            String publicId = String.format("%s/%s/%s",
                    restaurantName.replaceAll("\\s+", "_"),
                    categoryName.replaceAll("\\s+", "_"),
                    dishName.replaceAll("\\s+", "_"));

            // Set the desired image transformations, including resizing
            Map<?,?> uploadParams = ObjectUtils.asMap(
                    "public_id", publicId,
                    "overwrite", true,
                    "transformation",
                    new Transformation<>()
                            .width(AppConstants.IMAGE_WIDTH)
                            .height(AppConstants.IMAGE_HEIGHT)
                            .crop(AppConstants.IMAGE_CROP)
                            .quality(AppConstants.IMAGE_QUALITY)
                            .fetchFormat(AppConstants.FETCH_FORMAT)
            );

            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
            log.info("Image url : {} " , uploadResult.get("url"));
            return uploadResult.get("url").toString(); // Return the URL of the resized image

        } catch (Exception e) {
            throw new ImageUploadException("Failed to upload image");
        }
    }

}
