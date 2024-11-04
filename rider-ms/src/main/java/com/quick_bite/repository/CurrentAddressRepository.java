package com.quick_bite.repository;

import com.quick_bite.entity.CurrentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAddressRepository extends JpaRepository<CurrentAddress , Long> {
}
