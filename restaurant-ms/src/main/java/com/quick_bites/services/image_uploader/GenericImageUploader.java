package com.quick_bites.services.image_uploader;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.quick_bites.constants.AppConstants;
import com.quick_bites.exception.ImageUploadException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@Service
@Slf4j
@AllArgsConstructor
public class GenericImageUploader {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file ,  Long id) {
        try {
            // Define a custom public ID based on entity type and ID
            String publicId = String.format("%s", id);

            // Set the desired image transformations and public ID
            Map<?,?> uploadParams = ObjectUtils.asMap(
                    "public_id", publicId,
                    "overwrite", true,
                    "transformation",
                    new Transformation<>().width(AppConstants.IMAGE_WIDTH)
                            .height(AppConstants.IMAGE_HEIGHT)
                            .crop(AppConstants.IMAGE_CROP)
                            .quality(AppConstants.IMAGE_QUALITY)
                            .fetchFormat(AppConstants.FETCH_FORMAT)
            );

            // Upload image with specified parameters
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
            log.info("Image URL: {}", uploadResult.get("url"));
            return uploadResult.get("url").toString(); // Return the URL of the resized image

        } catch (Exception e) {
            throw new ImageUploadException("Failed to upload image");
        }

    }
}
