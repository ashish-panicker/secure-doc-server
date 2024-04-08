package com.easyskillup.sdoc.repository;

import com.easyskillup.sdoc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);
    Optional<User> findUserByUserId(String userId);
}
