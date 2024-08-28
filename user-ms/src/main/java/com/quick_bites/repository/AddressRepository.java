package com.quick_bites.repository;

import com.quick_bites.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Integer> {
}
