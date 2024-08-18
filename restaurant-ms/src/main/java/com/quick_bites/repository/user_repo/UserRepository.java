package com.quick_bites.repository.user_repo;

import com.quick_bites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {

}
