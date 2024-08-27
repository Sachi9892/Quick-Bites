package com.quick_bites.repository;

import com.quick_bites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {

    User findByUserMobileNumber(String mobileNumber);
}
