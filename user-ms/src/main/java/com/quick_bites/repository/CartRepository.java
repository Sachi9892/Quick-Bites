package com.quick_bites.repository;

import com.quick_bites.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Long> {

    Optional<Cart> findByUserId(Long userId);

}
