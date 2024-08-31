package com.quick_bites.service.managers.dish_rendering_manager.search;


import com.quick_bites.dto.dishdto.ResponseDishDto;
import com.quick_bites.entity.User;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.DishClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.util.List;
;

@Service
@AllArgsConstructor
@Slf4j
public class DishesByDistance {

    private final DishClient dishClient;
    private final UserRepository userRepository;

    /**
     * @param
     * @return List of dishes within 5kms of radius
     * @implNote = This method will render the list of dishes basis user location ( for the very first time when
     * once the user will render on home page
     */


    public List<ResponseDishDto> getDishesByDistance(Long userId , Double minDistance ,Double maxDistance) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoResourceFoundException("No resource found : " + userId));

        Double userLatitude = user.getAddress().getLatitude();
        Double userLongitude = user.getAddress().getLongitude();

        List<ResponseDishDto> dishes = dishClient.getDishesByDistance(
                userLatitude,
                userLongitude,
                minDistance,
                maxDistance
        );

        log.info("No of dishes returned :  {} " , dishes.size() );

        return dishes;

    }

}
