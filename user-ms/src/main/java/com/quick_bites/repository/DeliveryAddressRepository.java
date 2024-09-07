package com.quick_bites.repository;

import com.quick_bites.entity.DeliveryAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddresses , Long> {
}
