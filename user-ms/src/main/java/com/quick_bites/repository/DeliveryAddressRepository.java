package com.quick_bites.repository;

import com.quick_bites.entity.DeliveryAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddresses , Long> {


    List<DeliveryAddresses> findAddressesByUser_UserId(Long userId);

}
