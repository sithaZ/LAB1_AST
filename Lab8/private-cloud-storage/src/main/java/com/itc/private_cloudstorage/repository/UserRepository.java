package com.itc.private_cloudstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itc.private_cloudstorage.model.User; 

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}