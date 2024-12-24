package com.quick_bites.controller.image_uploader;


import com.quick_bites.exception.ImageUploadException;
import com.quick_bites.services.image_uploader.GenericImageUploader;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
@RequestMapping("restaurant/image")
public class ImageUploaderController {

    private final GenericImageUploader imageUploader;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") Long id) {

        try {
            // Upload the image and get the URL
            String imageUrl = imageUploader.uploadImage(file, id);
            return ResponseEntity.ok(imageUrl);

        } catch (ImageUploadException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Image upload failed: " + e.getMessage());
        }

    }

}
