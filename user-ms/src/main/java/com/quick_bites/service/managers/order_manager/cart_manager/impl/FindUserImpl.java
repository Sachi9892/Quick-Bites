package com.quick_bites.service.managers.order_manager.cart_manager.impl;


import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.IFindUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FindUserImpl implements IFindUser {


    private final CartRepository cartRepository;


    @Override
    public Long findUser(Long cartId) {

        Long userIdByCartId = cartRepository.findUserIdByCartId(cartId);
        log.info("User id {} " , userIdByCartId);
        return userIdByCartId;

    }
}
