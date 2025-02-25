package com.quick_bites.repository;

import com.quick_bites.entity.DeliveryAddresses;
import com.quick_bites.entity.UserDishReview;
import com.quick_bites.entity.User;
import com.quick_bites.entity.UserRestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByUserMobileNumber(String userMobileNumber);

    Optional<User> findByUserMobileNumberAndPassword(String userMobileNumber , String password);

}
