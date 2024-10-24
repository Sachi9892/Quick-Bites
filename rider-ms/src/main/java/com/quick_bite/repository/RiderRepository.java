package com.quick_bite.repository;

import com.quick_bite.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider , Long> {

}
