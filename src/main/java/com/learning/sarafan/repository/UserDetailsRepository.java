package com.learning.sarafan.repository;

import com.learning.sarafan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
