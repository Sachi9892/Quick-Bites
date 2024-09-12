package com.quick_bites.repository;

import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Long> {


    Optional<Cart> findByUserIdAndStatus(Long userId , CartStatus cartStatus);

    @Query("SELECT c.userId FROM Cart c WHERE c.cartId = :cartId")
    Long findUserIdByCartId(@Param("cartId") Long cartId);

}
