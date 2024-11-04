package com.quick_bite.repository;

import com.quick_bite.entity.LastLocationAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastAddress extends JpaRepository<LastLocationAddress , Long> {

}
